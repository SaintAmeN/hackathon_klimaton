package pl.ssh.pr.authorization.controller;

import pl.ssh.pr.common.model.user.request.AuthenticationRequestDto;
import pl.ssh.pr.common.model.user.response.AuthenticationResponseDto;
import pl.ssh.pr.authorization.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class handling requests which are provided for non-authenticated users
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * Authenticate user with provided credentials
     *
     * @param dto
     * @return authenticationResponseDto
     */
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto dto) {
        log.debug("Authentication attempt for user with name: " + dto.getUsername()  + ".");
        return authenticationService.authenticate(dto);
    }
}
