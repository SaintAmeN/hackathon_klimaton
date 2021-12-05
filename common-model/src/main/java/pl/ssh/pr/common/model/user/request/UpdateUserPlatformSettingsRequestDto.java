package pl.ssh.pr.common.model.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author amen
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Request to update platform settings of given user.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPlatformSettingsRequestDto {
    private String settings;
}
