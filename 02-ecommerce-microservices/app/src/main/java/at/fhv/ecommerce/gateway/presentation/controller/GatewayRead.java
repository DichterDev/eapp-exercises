package at.fhv.ecommerce.gateway.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import java.util.UUID;

@RestController
@RequestMapping("/api/gateway")
public class GatewayRead {

    private final RestClient restClient;

    public GatewayRead(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8080").build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<String> getUser(@PathVariable UUID userId) {
        return restClient.get()
            .uri("/api/users/{userId}", userId)
            .retrieve()
            .toEntity(String.class);
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<String> getUserOrders(@PathVariable UUID userId) {
        return restClient.get()
            .uri("/api/users/{userId}/orders", userId)
            .retrieve()
            .toEntity(String.class);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<String> getProduct(@PathVariable UUID productId) {
        return restClient.get()
            .uri("/api/products/{productId}", productId)
            .retrieve()
            .toEntity(String.class);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<String> getOrder(@PathVariable UUID orderId) {
        return restClient.get()
            .uri("/api/orders/{orderId}", orderId)
            .retrieve()
            .toEntity(String.class);
    }
}
