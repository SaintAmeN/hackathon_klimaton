package pl.ssh.pr.authorization.configuration.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Web security configuration
 */
@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {
    /**
     * Disable CSRF
     * Permit all requests with mapping {/authenticate, /token/refresh}
     * All other requests must be filtered
     *
     * @param http context
     * @return securityWebFilterChain
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        log.info("Initializing SecurityWebFilterChain. /authenticate and /token/refresh mappings are available for everyone.");
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/authenticate", "/token/refresh").permitAll()
                .anyExchange().permitAll()
                .and()
                .build();
    }

    /**
     * Password encoder instance responsible for encoding/decoding users' passwords
     *
     * @return bCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        log.info("Creating instance of password encoder: BCryptPasswordEncoder.");
        return new BCryptPasswordEncoder();
    }
}
