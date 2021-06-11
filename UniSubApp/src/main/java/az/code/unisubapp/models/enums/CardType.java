package az.code.unisubapp.models.enums;

public enum CardType {
    VISA("visa"),
    MASTERCARD("mastercard");

    public final String value;

    private CardType(String value) {
        this.value = value;
    }
}
