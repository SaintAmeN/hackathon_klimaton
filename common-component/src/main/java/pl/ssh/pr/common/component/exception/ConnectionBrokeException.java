package pl.ssh.pr.common.component.exception;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Exception thrown when certain type of connection breaks
 */
public class ConnectionBrokeException extends RuntimeException {
    public ConnectionBrokeException(String message) {
        super(message);
    }
}
