package pl.ssh.pr.common.component.exception;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Exception thrown when field value has an incorrect value
 */
public class InvalidFieldValueException extends RuntimeException {
    public InvalidFieldValueException(String message) {
        super(message);
    }
}
