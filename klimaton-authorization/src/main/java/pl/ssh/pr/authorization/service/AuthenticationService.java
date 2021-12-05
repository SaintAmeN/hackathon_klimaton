package pl.ssh.pr.authorization.service;

import pl.ssh.pr.common.component.exception.UsernameNotFoundException;
import pl.ssh.pr.common.component.exception.WrongPasswordException;
import pl.ssh.pr.common.model.user.request.AuthenticationRequestDto;
import pl.ssh.pr.common.model.user.response.AuthenticationResponseDto;
import pl.ssh.pr.authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class responsible for authenticating not logged in users/refreshing expired tokens.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder encoder;

    /**
     * Find user with desired username, check it's password, if ok return auth token, otherwise throw error.
     *
     * @param dto - user credentials : username and password
     * @return authenticationToken
     */
    public Mono<AuthenticationResponseDto> authenticate(AuthenticationRequestDto dto) {
        return userRepository.findByName(dto.getUsername())
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found!")))
                .filter(user -> encoder.matches(dto.getPassword(), user.getPassword()))
                .switchIfEmpty(Mono.error(new WrongPasswordException("Wrong password!")))
                .flatMap(tokenService::generateAccessToken)
                .map((token) -> new AuthenticationResponseDto(dto.getUsername(), token, tokenService.extractExpirationDate(token).getTime()));
    }
}
