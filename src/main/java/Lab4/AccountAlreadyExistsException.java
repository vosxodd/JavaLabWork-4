package Lab4;

public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException() {
        super();
    }

    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}
