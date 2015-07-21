package projectVT

class PersonalMessage {

    String possibleEmail
    String possibleName
    String message

    static mapping = {
        message type:'text'
    }

    static constraints = {
    }
}
