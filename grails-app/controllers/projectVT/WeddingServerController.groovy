package projectVT

import grails.converters.JSON

class WeddingServerController {

    def index(String email, String firstName, String lastName, String address, String message, String ip, int coming, int adults, int kids) {
        def wedding = null
        if(ip){
            wedding = Wedding.findByIp(ip)
        }

        wedding ?:  new Wedding(email: email, firstName: firstName, lastName: lastName, address: address, message: message, ip: ip, commingWedding: coming, adults: adults, kids: kids).save(failOnError: true)

        render([success: true] as JSON)
    }

    def ip(String ip){
        Wedding.findByIp(ip) ?: new Wedding(ip: ip).save()
    }
}
