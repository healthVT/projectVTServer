package projectVT

class SocialAuth {

    User user
    String socialMedia
    String socialId

    Date dateCreated = new Date()
    Date lastLogin = new Date()

    static constraints = {
        user nullable: false
        socialMedia nullable: false
        socialId nullable: false
    }
}
