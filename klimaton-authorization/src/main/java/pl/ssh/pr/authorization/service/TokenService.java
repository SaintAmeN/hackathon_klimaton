package pl.ssh.pr.authorization.service;

import pl.ssh.pr.common.component.jwt.JwtConfig;
import pl.ssh.pr.common.model.user.ApplicationUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class responsible for all operations linked with json web tokens
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {
    private final JwtConfig jwtConfig;

    /**
     * Generate token for a specific application user, all token specifications are determined in JwtConfig class
     *
     * @param user
     * @return token
     */
    public Mono<String> generateAccessToken(ApplicationUser user) {
        log.debug("Generating new token for " + user.getName() + ".");
        return Mono.just(Instant.now())
                .map(creationDate -> JWT.create()
                        .withSubject(user.getName())
                        .withIssuedAt(Date.from(creationDate))
                        .withExpiresAt(Date.from(creationDate.plusSeconds(jwtConfig.getExpiration())))
                        .withArrayClaim("roles", user.getRoles().toArray(new String[0]))
                        .sign(jwtConfig.getAlgorithm()));
    }

    /**
     * Check whether token has not expired
     *
     * @param token
     * @return isTokenValid
     */
    public Mono<Boolean> validateAccessToken(String token) {
        log.info("Validating token: " + token + ".");
        return Mono.just(token)
                .map(this::decodeToken)
                .map(Payload::getExpiresAt)
                .map(expirationDate -> expirationDate.after(Date.from(Instant.now())));
    }

    /**
     * Extract user from token
     *
     * @param token
     * @return username
     */
    public Mono<String> extractUsername(String token) {
        log.info("Extracting username from token " + token + ".");
        return Mono.just(token)
                .map(this::decodeToken)
                .map(Payload::getSubject);
    }

    /**
     * Extract roles from token
     *
     * @param token
     * @return userRoles
     */
    public Flux<String> extractRoles(String token) {
        log.info("Extracting roles date from token " + token + ".");
        return Mono.just(token)
                .map(this::decodeToken)
                .map(decodedJWT -> decodedJWT.getClaim("roles"))
                .filter(claim -> !claim.isNull())
                .flatMapIterable(claim -> claim.asList(String.class));
    }

    /**
     * Extract expiration date from token
     *
     * @param token
     * @return expirationDate
     */
    public Date extractExpirationDate(String token) {
        log.info("Extracting expiration date from token " + token + ".");
        return Mono.just(token)
                .map(this::decodeToken)
                .map(Payload::getExpiresAt)
                .block();
    }

    /**
     * Get decoded token from string
     *
     * @param token
     * @return decodedJWT
     */
    private DecodedJWT decodeToken(String token) {
        log.info("Decoding token: " + token + ".");
        return jwtConfig.getVerifier().verify(token);
    }
}
