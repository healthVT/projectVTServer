package projectVT

class IpAddress {

    String ip
    String agent
    String path

    Date dateCreated
    Date lastUpdated

    static constraints = {
        ip nullable: false
        agent nullable: true
        path nullable: true

        dateCreated nullable: true
        lastUpdated nullable: true
    }
}
