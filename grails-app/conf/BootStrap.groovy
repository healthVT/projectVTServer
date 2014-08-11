import projectVT.Role
import projectVT.User
import projectVT.UserRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)

        def testUser = new User(email: 'admin', password: 'admin', firstName: "Jay", lastName: "Chen").save(flush: true)

        new UserRole(role: Role.get(1), user: testUser).save(flush: true)
    }
    def destroy = {
    }
}
