import grails.converters.JSON
import projectVT.Role
import projectVT.User
import projectVT.UserDailyVitamin
import projectVT.UserRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)

        def testUser = new User(email: 'admin', password: 'admin', firstName: "Jay", lastName: "Chen").save(flush: true)

        new UserRole(role: Role.get(1), user: testUser).save(flush: true)


        JSON.registerObjectMarshaller(UserDailyVitamin){
            def returnArray = [:]

            returnArray['id'] = it.id
            returnArray['vitaminA'] = it.vitaminA
            returnArray['vitaminC'] = it.vitaminC
            returnArray['vitaminD'] = it.vitaminD
            returnArray['vitaminE'] = it.vitaminE
            returnArray['vitaminK'] = it.vitaminK
            returnArray['vitaminB1'] = it.vitaminB1
            returnArray['vitaminB2'] = it.vitaminB2
            returnArray['vitaminB3'] = it.vitaminB3
            returnArray['vitaminB6'] = it.vitaminB6
            returnArray['vitaminB12'] = it.vitaminB12
            returnArray['user'] = it.user


            return returnArray
        }
    }
    def destroy = {
    }
}
