package pl.ssh.pr.common.component.exception;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Exception thrown when trying to perform operation on connection properties when it is not established
 */
public class ConnectionNotEstablishedException extends RuntimeException {
    public ConnectionNotEstablishedException(String message) {
        super(message);
    }
}
