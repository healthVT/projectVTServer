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
                vitaminMap = [vitaminA: vi.vitaminA, vitaminB1: vi.vitaminB1, vitaminB12: vi.vitaminB12, vitaminB2: vi.vitaminB2, vitaminB3: vi.vitaminB3, vitaminB6: vi.vitaminB6, vitaminC: vi.vitaminC, vitaminD: vi.vitaminD, vitaminE: vi.vitaminE, vitaminK: vi.vitaminK, pantothenic: vi.pantothenic]
            }
        }

        result = [vitamin: vitaminMap, success: true]
        render result as JSON
    }
}
