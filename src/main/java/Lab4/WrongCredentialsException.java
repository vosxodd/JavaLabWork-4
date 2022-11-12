package Lab4;

public class WrongCredentialsException extends Exception {

    public WrongCredentialsException() {
        super();
    }

    public WrongCredentialsException(String message) {
        super(message);
    }
}
