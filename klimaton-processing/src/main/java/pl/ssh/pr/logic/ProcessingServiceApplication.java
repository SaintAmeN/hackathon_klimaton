package pl.ssh.pr.logic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProcessingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessingServiceApplication.class, args);
    }

}
