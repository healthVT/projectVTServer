package projectVT

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.codehaus.groovy.grails.web.json.JSONObject
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Secured(['ROLE_USER'])
class FoodController {
    def springSecurityService
    def utilService
    def index() { }

    def getFoodList(String category){
        def foods = Food.findAll()
        String foodList = ""
        foods.each(){ food->
            foodList += food.name + ","
        }

        def result = [foodList: foodList.substring(0, foodList.length()-1), success: true]
        render result as JSON
    }

    def getVitaminByFood(String foodName){
        def result = [:]
        Map vitaminMap = [:]
        def food = Food.findByName(foodName)
        if(food){

            def vitamin = Vitamin.findByFood(food)
            vitamin.each(){ vi ->
                vitaminMap = [a: vi.vitaminA, b1: vi.vitaminB1, b2: vi.vitaminB2, b3: vi.vitaminB3, b6: vi.vitaminB6, b12: vi.vitaminB12, c: vi.vitaminC, d: vi.vitaminD, e: vi.vitaminE, k: vi.vitaminK, pantothenic: vi.pantothenic]
            }
        }

        result = [vitamin: vitaminMap, foodName: foodName, success: true]
        render result as JSON
    }

    def getVitaminDailyResult(String foodList, String gender, int age){
        def foodAndAmountArray = foodList.split(",")

        int vitaminA = 0
        int vitaminB1 = 0
        int vitaminB2 = 0
        int vitaminB3 = 0
        int vitaminB6 = 0
        int vitaminB12 = 0
        int vitaminC = 0
        int vitaminD = 0
        int vitaminE = 0
        int vitaminK = 0

        gender = gender ?: "Male"
        def vitaminRequire = DailyAmount.findByGender(gender)
        Map requireMap = [a:vitaminRequire.vitaminA,
                b1:vitaminRequire.vitaminB1,
                b2:vitaminRequire.vitaminB2,
                b3:vitaminRequire.vitaminB3,
                b6:vitaminRequire.vitaminB6,
                b12:vitaminRequire.vitaminB12,
                c:vitaminRequire.vitaminC,
                d:vitaminRequire.vitaminD,
                e:vitaminRequire.vitaminE,
                k:vitaminRequire.vitaminK]

        foodAndAmountArray.each(){
            //Array 0 -> food name, 1 -> amount
            def array = it.split(":")
            def vitaminData = Vitamin.findByFood(Food.findByName(array[0]))

            if(vitaminData){
                double amount = Double.parseDouble(array[1]);
                vitaminA += vitaminData.vitaminA*amount
                vitaminB1 += vitaminData.vitaminB1*amount
                vitaminB2 += vitaminData.vitaminB2*amount
                vitaminB3 += vitaminData.vitaminB3*amount
                vitaminB6 += vitaminData.vitaminB6*amount
                vitaminB12 += vitaminData.vitaminB12*amount
                vitaminC += vitaminData.vitaminC*amount
                vitaminD += vitaminData.vitaminD*amount
                vitaminE += vitaminData.vitaminE*amount
                vitaminK += vitaminData.vitaminK*amount
            }


        }

        DecimalFormat df = new DecimalFormat("###");
        def resultMap = [
                a:  df.format(vitaminA/requireMap.a*100),
                b1: df.format(vitaminB1/requireMap.b1*100),
                b2: df.format(vitaminB2/requireMap.b2*100),
                b3: df.format(vitaminB3/requireMap.b3*100),
                b6: df.format(vitaminB6/requireMap.b6*100),
                b12:df.format(vitaminB12/requireMap.b12*100),
                c:  df.format(vitaminC/requireMap.c*100),
                d:  df.format(vitaminD/requireMap.d*100),
                e:  df.format(vitaminE/requireMap.e*100),
                k:  df.format(vitaminK/requireMap.k*100)]

        //task{recordIntoDatabase(foodAndAmountArray, resultMap)}

        try{

            User user = springSecurityService.getCurrentUser() as User
            def today = DateTime.now().withTime(0, 0, 0, 0)
            UserDailyFood.executeUpdate("DELETE UserDailyFood WHERE user = :user AND date BETWEEN :todayStart AND :todayEnd", [user: user, todayStart: today.toDate(), todayEnd: today.plusDays(1).toDate()])

            foodAndAmountArray.each(){
                def array = it.split(":")
                new UserDailyFood(user: user, food: Food.findByName(array[0]), amount: Integer.parseInt(array[1])).save(flush: true, failOnError: true)
            }

            UserDailyVitamin.executeUpdate("DELETE UserDailyVitamin WHERE user = :user AND date BETWEEN :todayStart AND :todayEnd", [user: user, todayStart: today.toDate(), todayEnd: today.plusDays(1).toDate()])

            new UserDailyVitamin(user: user, vitaminA: resultMap.a ,vitaminAIU: resultMap.a ,vitaminC: resultMap.c ,vitaminD: resultMap.d ,vitaminE: resultMap.e ,vitaminK: resultMap.k ,vitaminB1: resultMap.b1 ,vitaminB2: resultMap.b2 ,vitaminB3: resultMap.b3 ,vitaminB6: resultMap.b6 ,vitaminB12: resultMap.b12, dateString: utilService.jodaToString(today)).save(flush: true, failOnError: true)

        }catch(Exception e){
            log.error("Error on save daily record to database.", e)
        }

        render resultMap as JSON
    }

    def getVitaminRecord(String period){
        try{
            println period
            User user = springSecurityService.getCurrentUser() as User
            def datePeriod = utilService.convertPeriod(period)

            def record = UserDailyVitamin.executeQuery("select AVG(vitaminA) as A, AVG(vitaminB1) as B1, AVG(vitaminB2) as B2, AVG(vitaminB3) as B3, AVG(vitaminB6) as B6, AVG(vitaminB12) as B12, AVG(vitaminC) as C, AVG(vitaminD) as D, AVG(vitaminE) as E, AVG(vitaminK) as K, date from UserDailyVitamin WHERE date >= ? GROUP BY FROM_DAYS(TO_DAYS(date)-MOD(TO_DAYS(date), ?))", [datePeriod.startDate.toDate(), datePeriod.avgDatePeriod])

            List<Map> resultList = new ArrayList<Map>()
            SimpleDateFormat formatToString = new SimpleDateFormat("MM/dd")
            record.each(){
                //turn record into object
                def vitaminRecord = [A: it[0], B1: it[1], B2: it[2], B3: it[3], B6: it[4], B12: it[5], C: it[6], D: it[7], E: it[8], K: it[9], date: formatToString.format(it[10])]

                resultList.add(vitaminRecord)

            }
            def result = [success: true, vitaminRecordList: resultList]

            render result as JSON
        }catch(Exception e){
            log.error("Error on getting vitamin Record: ", e)
            render new JSONObject([success: false])
        }
    }


    public void recordIntoDatabase(def foodAndAmountArray, def resultMap){
        try{

            User user = springSecurityService.getCurrentUser() as User
            def today = DateTime.now().withTime(0, 0, 0, 0)
            UserDailyFood.executeUpdate("DELETE UserDailyFood WHERE user = :user AND date BETWEEN :todayStart AND :todayEnd", [user: user, todayStart: today.toDate(), todayEnd: today.plusDays(1).toDate()])

            foodAndAmountArray.each(){
                def array = it.split(":")
                new UserDailyFood(user: user, food: Food.findByName(array[0]), amount: Integer.parseInt(array[1])).save(flush: true, failOnError: true)
            }

            UserDailyVitamin.executeUpdate("DELETE UserDailyVitamin WHERE user = :user AND date BETWEEN :todayStart AND :todayEnd", [user: user, todayStart: today.toDate(), todayEnd: today.plusDays(1).toDate()])

            new UserDailyVitamin(user: user, vitaminA: resultMap.a ,vitaminAIU: resultMap.a ,vitaminC: resultMap.c ,vitaminD: resultMap.d ,vitaminE: resultMap.e ,vitaminK: resultMap.k ,vitaminB1: resultMap.b1 ,vitaminB2: resultMap.b2 ,vitaminB3: resultMap.b3 ,vitaminB6: resultMap.b6 ,vitaminB12: resultMap.b12).save(flush: true, failOnError: true)

        }catch(Exception e){
            log.error("Error on save daily record to database.", e)
            render new JSONObject([success: false])
        }

        render new JSONObject([success: true])
    }


}
