package az.code.unisubapp.models.enums;

public enum Plan {
    MONTHLY("month"),
    ANNUAL("annual");

    public final String value;

    private Plan(String value) {
        this.value = value;
    }
}
