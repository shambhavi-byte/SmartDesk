package smartdesk.exception;

public class SmartDeskException extends Exception {

    public SmartDeskException(String message) {
        super(message);
    }

    public SmartDeskException(String message, Throwable cause) {
        super(message, cause);
    }
}
