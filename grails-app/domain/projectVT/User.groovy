package projectVT

class User {

    String email
    String password
    String name
    int age = 0
    String gender
    int height = 0
    int weight = 0
    String ethnicity

    Date joinDate = new Date()
    static constraints = {
        email nullable: false
        password nullable: false
        name nullable: true
        gender nullable: false
        ethnicity nullable: false
    }
}
