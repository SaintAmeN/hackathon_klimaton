package pl.ssh.pr.gateway.configuration;

 import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

 import java.util.Arrays;
 import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 *
 * Since UI is nested in gateway, let requests asking for static content go through, /api prefix will be filtered after all
 */
@Component
public class ForwardWebFilter implements WebFilter {
    private final List<String> resources = Arrays.asList(
            "/api",
            "/static",
            "/asset-manifest.json",
            "/favicon.ico",
            "/index.html",
            "/manifest.json",
            "/service-worker.js"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (resources.stream().noneMatch(s -> exchange.getRequest().getURI().getPath().startsWith(s))) {
            return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().path("/index.html").build()).build());
        }
        return chain.filter(exchange);
    }
}
