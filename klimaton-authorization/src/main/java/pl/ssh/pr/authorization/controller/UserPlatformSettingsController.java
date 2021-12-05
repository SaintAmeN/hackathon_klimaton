package pl.ssh.pr.authorization.controller;

import pl.ssh.pr.common.component.rest.ResponseFactory;
import pl.ssh.pr.common.model.user.UserPlatformSettings;
import pl.ssh.pr.common.model.user.request.UpdateUserPlatformSettingsRequestDto;
import pl.ssh.pr.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author amen
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class handling CRUD requests associated with user platform settings.
 */
@RestController
@RequestMapping("/user/settings")
@RequiredArgsConstructor
@Slf4j
public class UserPlatformSettingsController {
    private final UserService userService;
    private final ResponseFactory responseFactory;

    /**
     * Find user platform settings of user with username provided in a request path
     *
     * @param username
     * @return user platform settings json string
     */
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<UserPlatformSettings>> findUserByID(@PathVariable(name = "username") String username) {
        log.debug("Finding user platform settings. Provided user ID: " + username);
        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, userService.findPlatformSettingsByUsername(username));
    }

    /**
     * Update platform settings for given user.
     *
     * @param username - user identifier whose settings will be updated
     * @param dto - data transfer object with new settings.
     * @return updated settings.
     */
    @PatchMapping("/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ResponseEntity<UserPlatformSettings>> findUserByID(@PathVariable(name = "username") String username, @RequestBody UpdateUserPlatformSettingsRequestDto dto) {
        log.debug("Updating user platform settings. Provided user username: " + username);
        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, userService.savePlatformSettingsByUsername(username, dto));
    }
}
