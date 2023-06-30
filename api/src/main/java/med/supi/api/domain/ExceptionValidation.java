package med.supi.api.domain;

public class ExceptionValidation extends RuntimeException {
    public ExceptionValidation(String message) {
        super(message);
    }
}
