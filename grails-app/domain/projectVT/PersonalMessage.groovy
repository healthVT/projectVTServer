package projectVT

class PersonalMessage {

    String possibleEmail
    String possibleName
    String message
    boolean readed = false

    static mapping = {
        message type:'text'
    }

    static constraints = {
    }
}
