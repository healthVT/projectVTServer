package projectVT

class UserDailyFood {

    User user
    Food food
    int amount
    Date date = new Date()

    static constraints = {
        user nullable: false
        food nullable: false
    }
}
