package pl.ssh.pr.common.component.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Configuration commonly used in services
 */
@Configuration
@Slf4j
public class CommonConfiguration {
    /**
     * Date-parsing utility, output example is: 2020-06-01 12:34:31
     *
     * @return DateTimeFormatter
     */
    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        log.info("DateTimeFormatter pattern is: yyyy-MM-dd HH-mm-ss.");
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
    }
}
