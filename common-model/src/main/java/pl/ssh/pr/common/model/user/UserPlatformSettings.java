package pl.ssh.pr.common.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author amen
 * @created 04/12/2021
 * @project klimaton
 * <p>
 */
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPlatformSettings {
    private String id;
    private String settings;
}
