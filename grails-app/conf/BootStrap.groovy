import grails.converters.JSON
import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils
import projectVT.Role
import projectVT.User
import projectVT.UserDailyVitamin
import projectVT.UserRole
import projectVT.Vitamin
import projectVT.VitaminDes

import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)


        def testUser = new User(email: 'admin', password: 'admin', firstName: "Jay", lastName: "Chen").save(flush: true)


        new UserRole(role: Role.get(1), user: testUser).save(flush: true)
        SpringSecurityUtils.clientRegisterFilter('socialAuthenticationFilter', SecurityFilterPosition.SECURITY_CONTEXT_FILTER.order + 50)

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

        JSON.registerObjectMarshaller(VitaminDes){
            def returnArray = [:]
            returnArray['vitaminName'] = it.vitaminName
            returnArray['description'] = it.description
        }

        JSON.registerObjectMarshaller(User){
            def returnArray = [:]
            returnArray['email'] = it.email
            returnArray['name'] = it.name
            returnArray['age'] = it.age
            returnArray['gender'] = it.gender
            returnArray['height'] = it.height
            returnArray['weight'] = it.weight
            returnArray['ethnicity'] = it.ethnicity

            return returnArray
        }
    }
    def destroy = {
    }
}
