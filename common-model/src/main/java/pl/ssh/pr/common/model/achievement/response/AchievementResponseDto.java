package pl.ssh.pr.common.model.achievement.response;

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
public class AchievementResponseDto {
    private String id;
    private String name;
    private int priority;

    private int numberOfOffers;
}
