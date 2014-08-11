package projectVT

class AuthenticationToken {

    String token
    String email

    static constraints = {
        token nullable: false
        email nullable: false
    }
}
