package pl.ssh.pr.common.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Data provided after successful authentication
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {
    private String username;
    private String token;
    private Long expiresAt;
}
