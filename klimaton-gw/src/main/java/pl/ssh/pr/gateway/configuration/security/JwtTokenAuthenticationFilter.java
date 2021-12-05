package pl.ssh.pr.gateway.configuration.security;

import pl.ssh.pr.common.component.exception.JwtTokenExtractException;
import pl.ssh.pr.common.component.jwt.JwtConfig;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Filter applied to every request.
 */
@Component
@Slf4j
public class JwtTokenAuthenticationFilter extends AbstractGatewayFilterFactory<JwtTokenAuthenticationFilter.Config> {
    private static final String WWW_AUTH_HEADER = "WWW-Authenticate";
    private final JwtConfig jwtConfig;

    @Autowired
    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {
        super(Config.class);
        this.jwtConfig = jwtConfig;
    }

    /**
     * Apply filter, if request is authenticated pass it through, otherwise throw 401 Unauthorized.
     * @param config
     * @return gatewayFilter
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            try {
                String token = this.extractToken(exchange.getRequest());
                DecodedJWT decodedJWT = jwtConfig.getVerifier().verify(token);


                ServerHttpRequest request = exchange.getRequest().mutate().header(
                        config.getHeaderName(), decodedJWT.getSubject()
                ).build();

                log.info("Token validation succeeded, forwarding request to a service.");
                return chain.filter(exchange.mutate().request(request).build());
            } catch (JwtTokenExtractException e) {
                log.error("Token issue occurred", e);
                return this.onError(exchange, HttpStatus.UNAUTHORIZED, e.getMessage());
            }
        };
    }

    /**
     * Error handler
     * @param exchange
     * @param httpStatus
     * @param err
     * @retur
     */
    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus, String err) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().add(WWW_AUTH_HEADER, this.formatErrorMsg(err));
        return response.setComplete();
    }

    /**
     * Find token in headers, if found remove prefix and return token as a String.
     * @param request
     * @return token
     */
    private String extractToken(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey("Authorization")) {
            throw new JwtTokenExtractException("Authorization header is missing");
        }
        List<String> headers = request.getHeaders().get(jwtConfig.getHeader());
        if (headers == null || headers.isEmpty()) {
            throw new JwtTokenExtractException("Authorization header is empty");
        }
        String header = headers.get(0).trim();
        if (!header.startsWith(jwtConfig.getPrefix())) {
            throw new JwtTokenExtractException("Bearer is needed");
        }
        log.info("Token found in request headers.");
        return header.replace(jwtConfig.getPrefix(), "").trim();
    }

    /**
     * Error log correlated with bearer authorization.
     * @param msg
     * @return formattedMsg
     */
    private String formatErrorMsg(String msg) {
        return String.format("Bearer error, error_description=\"%s\" ", msg);
    }

    /**
     * Class holding configuration for JwtAuthenticationFilter.
     */
    @Getter
    @Setter
    static class Config {
        private String headerName;
    }
}
