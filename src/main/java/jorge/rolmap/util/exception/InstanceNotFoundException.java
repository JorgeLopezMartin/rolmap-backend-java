package jorge.rolmap.util.exception;

public class InstanceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public InstanceNotFoundException() {}

    public InstanceNotFoundException(String message) {
        super(message);
    }

    public InstanceNotFoundException(Throwable cause) {
        super(cause);
    }

    public InstanceNotFoundException(String message, Throwable cause) {
        super(cause);
    }
}
