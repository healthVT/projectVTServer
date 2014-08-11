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

    String firstName
    String lastName
    String profilePhoto
    String zipCode
    Date birthday
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    Date joinDate = new Date()

    def springSecurityService

    static transients = ['springSecurityService']

    static constraints = {
        email nullable: false, unique: true
        password nullable: false
        name nullable: true
        gender nullable: false
        ethnicity nullable: false

        firstName nullable: true
        lastName nullable: true
        profilePhoto nullable: true
        zipCode nullable: true
        birthday nullable: true
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role }
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}
