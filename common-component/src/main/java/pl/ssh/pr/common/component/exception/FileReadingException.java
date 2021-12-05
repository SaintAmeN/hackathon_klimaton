package pl.ssh.pr.common.component.exception;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Event thrown when an error during reading a file occurs
 */
public class FileReadingException extends RuntimeException {
    public FileReadingException(String message) {
        super(message);
    }
}
