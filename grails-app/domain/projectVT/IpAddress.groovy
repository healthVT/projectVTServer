package projectVT

class IpAddress {

    String ip
    String agent

    Date dateCreated
    Date lastUpdated

    static constraints = {
        ip nullable: false
        agent nullable: true

        dateCreated nullable: true
        lastUpdated nullable: true
    }
}
