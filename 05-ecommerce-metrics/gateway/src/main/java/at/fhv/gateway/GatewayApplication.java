package at.fhv.gateway;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import lombok.RequiredArgsConstructor;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> productRoute() {
        return route("product")
            .route(path("/api/products/**"), http())
            .filter(lb("PRODUCT"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderRoute() {
        return route("order")
            .route(path("/api/orders/**"), http())
            .filter(lb("ORDER"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userRoute() {
        return route("user")
            .route(path("/api/users/**"), http())
            .filter(lb("USER"))
            .build();
    }
}
