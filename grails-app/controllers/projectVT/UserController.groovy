package projectVT

import grails.converters.JSON

class UserController {

    static scaffold = true

    def loginOrRegister(String email, String password, String name, int age, String gender, int height, int weight, String ethnicity, boolean register){
        def user = User.findByEmail(email)
        Map result
        try{
            if(user){
                if(password == user.password){
                    result = [success: true, message: ""]
                }else{
                    result = [success: false, message: "Wrong password"]
                }
            }else{
                if(register){
                    new User(email: email, password: password, name: name, age: age, gender: gender, height: height, weight: weight, ethnicity: ethnicity).save(flush:true, failOnError: true)
                    result = [success: true, message: ""]
                }else{
                    result = [success: false, register: true, message: "Please Register"]
                }

            }
        }catch(Exception e){
            log.error("Login or register error: \n" + e);
            result = [success: false, message: "The server incounter error stage, please try it again later."]
        }


        render result as JSON
    }
}
