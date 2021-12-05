package pl.ssh.pr.common.component.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Event sent when FileReader reads a new line.
 */
@Getter
public class LineReadEvent extends ApplicationEvent {
    private String line;

    public LineReadEvent(Object source) {
        super(source);
        line = (String) source;
    }
}
