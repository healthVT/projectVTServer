import grails.converters.JSON
import projectVT.Role
import projectVT.User
import projectVT.UserDailyVitamin
import projectVT.UserRole

import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)

        def testUser = new User(email: 'admin', password: 'admin', firstName: "Jay", lastName: "Chen").save(flush: true)

        new UserRole(role: Role.get(1), user: testUser).save(flush: true)


        JSON.registerObjectMarshaller(UserDailyVitamin){
            def returnArray = [:]

            SimpleDateFormat formatToString = new SimpleDateFormat("MM/dd")

            returnArray['id'] = it.id
            returnArray['A'] = it.vitaminA
            returnArray['C'] = it.vitaminC
            returnArray['D'] = it.vitaminD
            returnArray['E'] = it.vitaminE
            returnArray['K'] = it.vitaminK
            returnArray['B1'] = it.vitaminB1
            returnArray['B2'] = it.vitaminB2
            returnArray['B3'] = it.vitaminB3
            returnArray['B6'] = it.vitaminB6
            returnArray['B12'] = it.vitaminB12
            returnArray['date'] = formatToString.format(it.date)

            return returnArray
        }
    }
    def destroy = {
    }
}
