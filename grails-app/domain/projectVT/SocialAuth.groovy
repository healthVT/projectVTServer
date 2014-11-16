package projectVT

class SocialAuth {

    User user
    String socialMedia
    String socialId

    Date dateCreated
    Date lastLogin

    static constraints = {
        user nullable: false
        socialMedia nullable: false
        socialId nullable: false
    }
}
