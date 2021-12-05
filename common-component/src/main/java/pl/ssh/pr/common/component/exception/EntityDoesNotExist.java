package pl.ssh.pr.common.component.exception;

/**
 * @author amen
 * @created 04/12/2021
 * @project klimaton
 * <p>
 */
public class EntityDoesNotExist extends RuntimeException {
    public EntityDoesNotExist(String message) {
        super(message);
    }
}
