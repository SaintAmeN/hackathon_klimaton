package pl.ssh.pr.common.component.delegate;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Strategy purposed interface, determines whether delegate source should be investigated
 */
public interface IDelegateSourceCheckCondition {
    boolean shouldCheck();
}
