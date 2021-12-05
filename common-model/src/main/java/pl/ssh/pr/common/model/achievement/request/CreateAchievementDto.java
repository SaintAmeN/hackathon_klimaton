package pl.ssh.pr.common.model.achievement.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Paweł Recław, AmeN
 * @project klimaton
 * @created 04/12/2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAchievementDto {
    private String name;
}