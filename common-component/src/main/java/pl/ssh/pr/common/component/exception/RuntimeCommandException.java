package pl.ssh.pr.common.component.exception;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Exception thrown when error occurs during shell command execution
 */
public class RuntimeCommandException extends RuntimeException {
    public RuntimeCommandException(String message) {
        super(message);
    }
}
