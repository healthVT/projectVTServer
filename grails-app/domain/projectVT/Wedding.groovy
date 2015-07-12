package projectVT

class Wedding {

    String email
    int commingWedding
    String firstName
    String lastName
    String address
    int adults
    int kids
    String message
    String ip
    Date dateCreated
    int loginTimes

    static mapping = {
        address type: 'text'
        message type: 'text'
    }

    static constraints = {
        email nullable: false
        firstName nullable: true
        lastName nullable: true
        address nullable: true
        message nullable: true
        ip nullable: true
        dateCreated nullable: true
    }
}
