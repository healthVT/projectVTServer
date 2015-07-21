package projectVT

import grails.converters.JSON
import org.apache.commons.codec.binary.Base64

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

    def getPersonalMessage(String encodedEmail, String encodedName){
        byte[] decodedEmail = encodedEmail ? Base64.decodeBase64(encodedEmail) : null
        byte[] decodedName = encodedName ? Base64.decodeBase64(encodedName) : null

        String email = decodedEmail ? new String(decodedEmail, "UTF-8") : null
        String name = decodedName ? new String(decodedName, "UTF-8") : null
        println email
        println name

        def personalMessage = null
        if(email){
            personalMessage = PersonalMessage.findAllByPossibleEmailLike("%$email%")
        }else if(name){
            def nameArray = name.split(' ')
            def query = ""
            nameArray.each() {
                query += "%" + it + "%"
            }

            println query
            personalMessage = PersonalMessage.findAllByPossibleNameLike(query)
        }

        if(personalMessage && personalMessage.size() > 1){
            log.error("I got mutiple result!!!!!!!!!!!!!!!!! $name, $email")

        }

        render([success: true, message: personalMessage?.size() == 1 ? personalMessage?.first() : null] as JSON)
    }
}
