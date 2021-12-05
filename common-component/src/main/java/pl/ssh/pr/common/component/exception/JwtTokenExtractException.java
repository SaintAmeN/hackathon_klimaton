package pl.ssh.pr.common.component.exception;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Exception thrown when an error occurs during extracting a token
 */
public class JwtTokenExtractException extends RuntimeException {
    public JwtTokenExtractException(String message) {
        super(message);
    }
}
