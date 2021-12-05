package pl.ssh.pr.authorization.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "pl.ssh.pr.authorization",
        "pl.ssh.pr.common.model.user",
        "pl.ssh.pr.common.component.jwt",
        "pl.ssh.pr.common.component.rest",
        "pl.ssh.pr.common.component.util"
})
public class ComponentConfiguration {
}
