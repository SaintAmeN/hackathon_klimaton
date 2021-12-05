package pl.ssh.pr.logic.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "pl.ssh.pr.logic",
        "pl.ssh.pr.common.model.achievement",
        "pl.ssh.pr.common.component.rest",
        "pl.ssh.pr.common.component.util"
})
public class ComponentConfiguration {
}
