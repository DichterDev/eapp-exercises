package at.fhv.ecommerce.order.write.infrastructure.client;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import at.fhv.ecommerce.order.write.application.client.ProductClient;
import lombok.RequiredArgsConstructor;

@Component
public class RestProductClient implements ProductClient {
    private final RestClient client;

    public RestProductClient(RestClient.Builder builder) {
        this.client = builder.baseUrl("localhost:8081/api/products").build();
    }

    @Override
    public void reduceStock(String productId, Integer amount) {
        client.post()
            .uri("/{id}/reduce-stock", productId)
            .body(amount)
            .retrieve()
            .toBodilessEntity();
    }

    @Override
    public Double getPrice(String productId) {
        return client.get()
            .uri("/{id}/price", productId)
            .retrieve()
            .body(Double.class);
    }
}
