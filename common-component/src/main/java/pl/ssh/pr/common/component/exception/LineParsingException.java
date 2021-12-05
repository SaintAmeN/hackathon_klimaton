package pl.ssh.pr.common.component.exception;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Exception thrown when an error occurs during parsing a line
 */
public class LineParsingException extends RuntimeException {
    public LineParsingException(String message) {
        super(message);
    }
}
