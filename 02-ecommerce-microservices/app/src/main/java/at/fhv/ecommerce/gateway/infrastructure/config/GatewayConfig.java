package at.fhv.ecommerce.gateway.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;

@Configuration
public class GatewayConfig {

    @Value("${SERVICES_PRODUCT_URL:http://product-service:8080}")
    private String productServiceUrl;

    @Value("${SERVICES_ORDER_URL:http://order-service:8080}")
    private String orderServiceUrl;

    @Value("${SERVICES_USER_URL:http://user-service:8080}")
    private String userServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> customRoutes() {
        return route("product_service")
            .route(path("/api/products/**"), http())
            .before(uri(productServiceUrl))
            .build()
            .and(
                route("product_service_docs")
                    .route(path("/product/v3/api-docs"), http())
                    .before(rewritePath("/product/v3/api-docs", "/v3/api-docs"))
                    .before(uri(productServiceUrl + "/v3/api-docs"))
                    .build()
            )
            .and(
                route("order_service")
                    .route(path("/api/orders/**"), http())
                    .before(uri(orderServiceUrl))
                    .build()
            )
            .and(
                route("order_service_docs")
                    .route(path("/order/v3/api-docs"), http())
                    .before(rewritePath("/order/v3/api-docs", "/v3/api-docs"))
                    .before(uri(orderServiceUrl + "/v3/api-docs"))
                    .build()
            )
            .and(
                route("user_service")
                    .route(path("/api/users/**"), http())
                    .before(uri(userServiceUrl))
                    .build()
            )
            .and(
                route("user_service_docs")
                    .route(path("/user/v3/api-docs"), http())
                    .before(rewritePath("/user/v3/api-docs", "/v3/api-docs"))
                    .before(uri(userServiceUrl + "/v3/api-docs"))
                    .build()
            );
    }
}
