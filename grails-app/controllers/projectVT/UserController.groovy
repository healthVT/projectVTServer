package projectVT

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken


class UserController {

    static scaffold = true

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

}
