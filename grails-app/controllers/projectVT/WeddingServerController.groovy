package projectVT

import grails.converters.JSON

class WeddingServerController {

    def index(String email, String firstName, String lastName, String address, String message, String ip, int coming, int adults, int kids) {
        def wedding = Wedding.findByEmail(email) ?:  new Wedding(email: email)

        wedding.firstName=  firstName
        wedding.lastName = lastName
        wedding.address = address
        wedding.message = message
        wedding.commingWedding = coming
        wedding.adults = adults
        wedding.kids = kids

        wedding.save(failOnError: true, flush: true)

        render([success: true] as JSON)
    }
/*
    def saveIp(String ip){
        def wedding = Wedding.findByIp(ip) ?: new Wedding(ip: ip)
        wedding.loginTimes = wedding.loginTimes + 1
        wedding.save(failOnError: true, flush: true)

        render([success: true] as JSON)
    }*/

    def getPersonalMessage(String email, String name){
        def personalMessage = null
        if(email){
            personalMessage = PersonalMessage.findAllByPossibleEmailLike(email)
        }else if(name){
            def nameArray = name.split(' ')
            def query = ""
            nameArray.each() {
                query += "%" + it + "%"
            }
            personalMessage = PersonalMessage.findAllByPossibleNameLike(query)
        }

        if(personalMessage.size() > 0){
            log.error("I got mutiple result!!!!!!!!!!!!!!!!! $name, $email")

            render([success: true, message: null] as JSON)
        }
        render([success: true, message: personalMessage?.first()] as JSON)
    }
}
