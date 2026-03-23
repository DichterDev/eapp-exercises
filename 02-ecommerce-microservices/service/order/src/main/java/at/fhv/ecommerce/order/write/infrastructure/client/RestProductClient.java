package at.fhv.ecommerce.order.write.infrastructure.client;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import at.fhv.ecommerce.order.write.application.client.ProductClient;

@Component
public class RestProductClient implements ProductClient {
    private final RestClient client;

    public RestProductClient(
        RestClient.Builder builder,
        @Value("${services.product.url}") String productServiceUrl) {
        this.client = builder.baseUrl(productServiceUrl + "/api/products").build();
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
