package pl.ssh.pr.common.model.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.ssh.pr.common.model.user.ApplicationUser;
import pl.ssh.pr.common.model.user.response.ApplicationUserResponseDto;
import pl.ssh.pr.common.model.user.request.RegisterApplicationUserRequestDto;

@Mapper(componentModel = "spring")
public interface ApplicationUserMapper {
    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "roles", target = "roles")
    })
    ApplicationUser applicationUserRequestDtoToApplicationUser(RegisterApplicationUserRequestDto dto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    ApplicationUserResponseDto applicationUserToApplicationUserResponseDto(ApplicationUser user);
}