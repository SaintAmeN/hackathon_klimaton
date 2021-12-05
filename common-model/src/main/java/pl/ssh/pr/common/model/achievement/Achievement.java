package pl.ssh.pr.common.model.achievement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.ssh.pr.common.model.user.ApplicationUser;

import java.time.LocalDateTime;

/**
 * @author Paweł Recław, AmeN
 * @project klimaton
 * @created 04/12/2021
 *
 * This class can represent for example: single walk to school, bike ride.
 * User will have multiple achievements that will be summarized as his total score.
 *
 * Scores will be archived after season passes.
 */
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Achievement {
    @Id
    private String id;
    private String name;
    private int award;

    private LocalDateTime dateTimeCreated;
    private ApplicationUser creator;


}
