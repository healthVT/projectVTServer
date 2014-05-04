package projectVT

public enum Gender{
    MALE(1), FEMALE(2)
    private int value;

    private Gender(int value){
        this.value = value;
    }
}

class DailyAmount {

    int minAge
    int maxAge
    String gender
    double vitaminA
    double vitaminARAE
    double vitaminAIU
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
    }
}
