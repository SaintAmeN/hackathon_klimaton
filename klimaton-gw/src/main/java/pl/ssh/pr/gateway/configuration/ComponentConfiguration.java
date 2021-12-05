package pl.ssh.pr.gateway.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 *
 * Configure what packages should be added to application context.
 */
@Configuration
@ComponentScan(basePackages = {
        "pl.ssh.pr.gateway",
        "pl.ssh.pr.common.component.jwt"
})
public class ComponentConfiguration {
}
