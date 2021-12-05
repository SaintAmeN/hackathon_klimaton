package pl.ssh.pr.common.component.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class responsible for holding all constants and objects responsible for json web tokens management
 */
@Component
@Getter
@Setter
@Slf4j
public class JwtConfig {
    @Value("${security.jwt.uri:/auth/**}")
    private String uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.prefix.length:7}")
    private int prefixLength;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:Klimaton2021Wroclaw!}")
    private String secret;

    private Algorithm algorithm;
    private JWTVerifier verifier;

    /**
     * Initialize JWT algorithm
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initializeAlgorithm() {
        log.info("JWT Verifier with encrypting algorithm initialized.");
        algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm).build();
    }
}
