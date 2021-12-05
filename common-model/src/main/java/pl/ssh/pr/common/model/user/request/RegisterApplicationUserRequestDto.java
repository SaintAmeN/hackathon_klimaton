package pl.ssh.pr.common.model.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Data needed to register user to the system
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterApplicationUserRequestDto {
    private String name;
    private String password;
    private String confirmPassword;
    private List<String> roles;
}
