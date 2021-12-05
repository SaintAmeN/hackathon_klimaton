package pl.ssh.pr.common.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class represents system user
 */
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser {
    @Id
    private String id;
    private String name;
    private String password;
    private Collection<String> roles;
    private UserPlatformSettings platformSettings;
}
