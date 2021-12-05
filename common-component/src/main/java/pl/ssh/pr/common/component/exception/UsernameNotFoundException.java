package pl.ssh.pr.common.component.exception;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 *
 * Exception thrown when attempt to find a user with some username was unsuccessful
 */
public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
