package projectVT

class Wedding {

    String email
    int commingWedding
    String firstName
    String address
    int adults
    int kids
    String message
    String ip
    Date dateCreated
    int loginTimes
    int vegetarian
    int kidChair

    static mapping = {
        address type: 'text'
        message type: 'text'
    }

    static constraints = {
        email nullable: true, unique: true
        firstName nullable: true
        address nullable: true
        message nullable: true
        ip nullable: true
        dateCreated nullable: true
    }
}
