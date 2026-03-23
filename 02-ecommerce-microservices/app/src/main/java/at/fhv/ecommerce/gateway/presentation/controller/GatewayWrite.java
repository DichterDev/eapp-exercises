package at.fhv.ecommerce.gateway.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import at.fhv.ecommerce.gateway.presentation.command.CreateProductCommand;
import at.fhv.ecommerce.gateway.presentation.command.PlaceOrderCommand;
import at.fhv.ecommerce.gateway.presentation.command.RegisterUserCommand;
import at.fhv.ecommerce.gateway.presentation.request.AddToCartRequest;
import at.fhv.ecommerce.gateway.presentation.request.CreateProductRequest;
import at.fhv.ecommerce.gateway.presentation.request.PlaceOrderRequest;
import at.fhv.ecommerce.gateway.presentation.request.RegisterUserRequest;
import at.fhv.ecommerce.gateway.presentation.response.GatewayIdResponse;
import java.util.UUID;

@RestController
@RequestMapping("/api/gateway")
public class GatewayWrite {

    private final RestClient restClient;

    public GatewayWrite(RestClient.Builder builder) {
        // Points to the Gateway's own port to use internal RouterFunction definitions
        this.restClient = builder.baseUrl("http://localhost:8080").build();
    }

    @PostMapping("/users/register")
    public ResponseEntity<GatewayIdResponse> register(@RequestBody RegisterUserRequest req) {
        UUID userId = UUID.randomUUID();
        var command = new RegisterUserCommand(userId, req.name());

        restClient.post()
            .uri("/api/users/register")
            .body(command)
            .retrieve()
            .toBodilessEntity();

        return ResponseEntity.ok(new GatewayIdResponse(userId.toString()));
    }

    @PostMapping("/products/create")
    public ResponseEntity<GatewayIdResponse> createProduct(@RequestBody CreateProductRequest req) {
        UUID productId = UUID.randomUUID();
        var command = new CreateProductCommand(
            productId,
            req.name(),
            req.description(),
            req.price(),
            req.stock()
        );

        restClient.post()
            .uri("/api/products/create")
            .body(command)
            .retrieve()
            .toBodilessEntity();

        return ResponseEntity.ok(new GatewayIdResponse(productId.toString()));
    }

    @PostMapping("/users/cart/add")
    public ResponseEntity<Void> addToCart(
        @RequestBody AddToCartRequest req) {
        restClient.post()
            .uri("/api/users/{userId}/cart/add", req.userId())
            .body(req)
            .retrieve()
            .toBodilessEntity();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{userId}/cart/checkout")
    public ResponseEntity<Void> checkout(@PathVariable UUID userId) {
        restClient.post()
            .uri("/api/users/{userId}/cart/checkout", userId)
            .retrieve()
            .toBodilessEntity();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/orders/place")
    public ResponseEntity<GatewayIdResponse> placeOrder(@RequestBody PlaceOrderRequest req) {
        UUID orderId = UUID.randomUUID();
        var command = new PlaceOrderCommand(orderId, req.userId(), req.items());

        restClient.post()
            .uri("/api/orders/place")
            .body(command)
            .retrieve()
            .toBodilessEntity();

        return ResponseEntity.ok(new GatewayIdResponse(orderId.toString()));
    }

    @PostMapping("/orders/{orderId}/complete")
    public ResponseEntity<Void> completeOrder(@PathVariable UUID orderId) {
        restClient.post()
            .uri("/api/orders/{orderId}/complete", orderId)
            .retrieve()
            .toBodilessEntity();
        return ResponseEntity.ok().build();
    }
}
