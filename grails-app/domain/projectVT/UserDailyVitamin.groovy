package projectVT

class UserDailyVitamin {

    User user
    Date date = new Date()
    String dateString

    double vitaminARAE
    double vitaminAIU
    double vitaminA
    double vitaminC
    double vitaminD
    double vitaminE
    double vitaminK
    double vitaminB1
    double vitaminB2
    double vitaminB3
    double vitaminB6
    double vitaminB12
    double pantothenic

    static constraints = {
        user nullable: false
        dateString nullable: true
    }
}
