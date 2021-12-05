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
 */
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AchievementType {
    @Id
    private String id;

    private ApplicationUser creator;
    private LocalDateTime dateTimeCreated;


}
