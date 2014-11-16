package projectVT

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken


class UserController {

    static scaffold = true
    def springSecurityService

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def loginOrRegister(String email, String password, String name, int age, String gender, int height, int weight, String ethnicity, boolean register){
        def user = User.findByEmail(email)
        Map result = [:]
        try{
            if(user){
                //User exists
                result = [success: false, login: true]
            }else{
                if(register){
                    user = new User(email: email, password: password, name: name, age: age, gender: gender, height: height, weight: weight, ethnicity: ethnicity).save(flush:true, failOnError: true)
                    new UserRole(user: user, role: Role.findByAuthority("ROLE_USER")).save(flush: true, failOnError: true)
                    result = [success: true, login: true]
                }else{
                    result = [success: false, register: true, message: "Please Register"]
                }

            }
        }catch(Exception e){
            log.error("Login or register error: ", e);
            result = [success: false, message: "The server incounter error stage, please try it again later."]
        }

        if(result.success){
            redirect(controller: "login", params: [email: email, password: password])
        }

        render result as JSON
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def isUserRegistered(String email, String displayName, String gender, String birthdayString, String socialId, String socialMedia){
        User user = User.findByEmail(email)

        if(!user){
            render([success: true, register: false] as JSON)
            return
        }

        def auth = SocialAuth.findBySocialId(socialId) ?: new SocialAuth(user: user, socialMedia: socialMedia, socialId: socialId).save(failOnError: true)

        def birthday = birthdayString ? new Date(birthdayString) : null
        user.birthday = user.birthday ?: birthday
        user.name = user.name ?: displayName
        user.gender = user.gender ?: gender

        user.save(failOnError: true)

        render([success: true, register: true, socialConnect: true] as JSON)
    }

    @Secured(['ROLE_USER'])
    def getUserInfo(){
        User user = springSecurityService.getCurrentUser() as User

        def result = [success: true, user: user]
        if(!user){
            result = [success: false]
        }

        render result as JSON
    }

    @Secured(['ROLE_USER'])
    def updateUserInfo(String email, String name, int age, String gender, float height, float weight, String ethnicity){
        User user = springSecurityService.getCurrentUser() as User

        if(!user){
            render ([success: false] as JSON)
        }

        user.email = email
        user.name = name
        user.age = age
        user.gender = gender
        user.height = height
        user.weight = weight
        user.ethnicity = ethnicity

        user.save(failOnError: true, flush: true)

        render([success: true] as JSON)
    }



}
