package projectVT

import grails.converters.JSON

class VitaminController {

    def description(String vitaminName){
        def vitamin = VitaminDes.findByVitaminName(vitaminName)

        def result
        if(vitamin){
            result = [success: true, vitaminDescription: vitamin]
        }else{
            result = [success: false]
        }

        render result as JSON
    }

}
