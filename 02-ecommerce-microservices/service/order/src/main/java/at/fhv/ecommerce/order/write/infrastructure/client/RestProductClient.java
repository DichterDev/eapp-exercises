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
    public void reduceStock(UUID productId, Integer amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reduceStock'");
    }

    @Override
    public Double getPrice(UUID productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrice'");
    }
}
