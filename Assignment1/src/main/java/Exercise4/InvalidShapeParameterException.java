package Exercise4;

public class InvalidShapeParameterException extends Exception {
    private static final String MSG = "Shape parameter is not valid!";
    public InvalidShapeParameterException() {
        super(MSG);
    }
}
