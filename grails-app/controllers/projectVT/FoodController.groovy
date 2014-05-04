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

        def vitaminRequire = DailyAmount.findByGender(gender)
        Map resultMap = [a:vitaminRequire.vitaminA,
                b1:vitaminRequire.vitaminB1,
                b2:vitaminRequire.vitaminB2,
                b3:vitaminRequire.vitaminB3,
                b6:vitaminRequire.vitaminB6,
                b12:vitaminRequire.vitaminB12,
                c:vitaminRequire.vitaminC,
                d:vitaminRequire.vitaminD,
                e:vitaminRequire.vitaminE,
                k:vitaminRequire.vitaminK];

        foodAndAmountArray.each(){ foodAndAmount->
            //Array 0 -> food name, 1 -> amount
            def array = foodAndAmount.split(":")

            def vitaminData = Vitamin.findByFood(Food.findByName(array[0]))
            if(vitaminData){
                double amount = vitaminData.gram*Double.parseDouble(array[1]);
                resultMap = [a: resultMap.a - (vitaminData.vitaminA*amount),
                        b1: resultMap.b1 - (vitaminData.vitaminB1*amount),
                        b2: resultMap.b2 - (vitaminData.vitaminB2*amount),
                        b3: resultMap.b3 - (vitaminData.vitaminB3*amount),
                        b6: resultMap.b6 - (vitaminData.vitaminB6*amount),
                        b12: resultMap.b12  - (vitaminData.vitaminB12*amount),
                        c: resultMap.c - (vitaminData.vitaminC*amount),
                        d: resultMap.d - (vitaminData.vitaminD*amount),
                        e: resultMap.e - (vitaminData.vitaminE*amount),
                        k: resultMap.k - (vitaminData.vitaminK*amount)]
            }
        }

        render resultMap as JSON
    }
}
