package HomeWork6.dto.enums;

public enum Banks {
    NBRB ("НБРБ"),
    ALPHABANK ("Альфа банк"),
    BELARUSBANK ("Беларусбанк"),
    BELAGROPROMBANK ("Белагропромбанк");

    private final String name;

    Banks(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
