package projectVT

import grails.converters.JSON


class FoodController {

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

        result = [vitamin: vitaminMap, success: true]
        render result as JSON
    }

    def getVitaminDailyResult(String foodList, String gender, int age){
        println foodList;
        println gender;
        def foodAndAmountArray = foodList.split(",")
        Map resultMap = [a:0,b1:0,b2:0,b3:0,b6:0,b12:0,c:0,d:0,e:0,k:0]
        def vitaminRequire = DailyAmount.findByGender(gender)
        foodAndAmountArray.each(){ foodAndAmount->
            //Array 0 -> food name, 1 -> amount
            def array = foodAndAmount.split(":")

            def vitaminData = Vitamin.findByFood(Food.findByName(array[0]))
            if(vitaminData){
                double amount = vitaminData.gram*Double.parseDouble(array[1]);
                resultMap = [a: resultMap.a + vitaminRequire.vitaminA - (vitaminData.vitaminA*amount),
                        b1: resultMap.b1 + vitaminRequire.vitaminB1 - (vitaminData.vitaminB1*amount),
                        b2: resultMap.b2 + vitaminRequire.vitaminB2 - (vitaminData.vitaminB2*amount),
                        b3: resultMap.b3 + vitaminRequire.vitaminB3 - (vitaminData.vitaminB3*amount),
                        b6: resultMap.b6 + vitaminRequire.vitaminB6 - (vitaminData.vitaminB6*amount),
                        b12: resultMap.b12 + vitaminRequire.vitaminB12 - (vitaminData.vitaminB12*amount),
                        c: resultMap.c + vitaminRequire.vitaminC - (vitaminData.vitaminC*amount),
                        d: resultMap.d + vitaminRequire.vitaminD - (vitaminData.vitaminD*amount),
                        e: resultMap.e + vitaminRequire.vitaminE - (vitaminData.vitaminE*amount),
                        k: resultMap.k + vitaminRequire.vitaminK - (vitaminData.vitaminK*amount)]
            }
        }

        render resultMap as JSON
    }
}
