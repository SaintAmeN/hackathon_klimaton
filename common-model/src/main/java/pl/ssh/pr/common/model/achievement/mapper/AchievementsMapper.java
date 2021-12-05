package pl.ssh.pr.common.model.achievement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.ssh.pr.common.model.achievement.Achievement;
import pl.ssh.pr.common.model.achievement.request.AchievementRequestDto;
import pl.ssh.pr.common.model.achievement.response.AchievementResponseDto;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 */
@Mapper(componentModel = "spring")
public interface AchievementsMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
    })
    AchievementResponseDto achievementToResponseDto(Achievement source);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
    })
    Achievement achievementRequestToAchievement(AchievementRequestDto dto);
}
