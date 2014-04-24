package projectVT

import grails.converters.JSON


class FoodController {

    def index() { }

    def getFoodList(String category){
        render Food.findAll() as JSON
    }
}
