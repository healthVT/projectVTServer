package projectVT

import grails.converters.JSON

class WeddingServerController {

    def index(String email, String firstName, String lastName, String address, String message, String ip, int coming, int adults, int kids) {
        def wedding = null
        if(ip){
            wedding = Wedding.findByIp(ip)
        }

         wedding = wedding ?:  new Wedding(ip: ip)

        wedding.email = email
        wedding.firstName=  firstName
        wedding.lastName = lastName
        wedding.address = address
        wedding.message = message
        wedding.commingWedding = coming
        wedding.adults = adults
        wedding.kids = kids

        wedding.save(failOnError: true)

        render([success: true] as JSON)
    }

    def saveIp(String ip){
        def wedding = Wedding.findByIp(ip) ?: new Wedding(ip: ip)
        wedding.loginTimes = wedding.loginTimes + 1
        wedding.save(failOnError: true)

        render([success: true] as JSON)
    }
}
