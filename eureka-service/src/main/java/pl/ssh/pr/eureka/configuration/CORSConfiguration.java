package pl.ssh.pr.eureka.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Configure Cross-Origin Resource Sharing
 */
@Configuration
@EnableWebMvc
public class CORSConfiguration implements WebMvcConfigurer {
    /**
     * Let Eureka use it's static content like javascript, stylesheets
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/eureka/**")
                .addResourceLocations("/eureka/", "classpath:/static/eureka/");
    }


    /**
     * Set the default return content type to JSON
     * TODO : Check why it was set to XML as default
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * Allow every host to access these resources
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
