package at.fhv.ecommerce.user.write.infrastructure.client;

import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import at.fhv.ecommerce.user.write.application.client.OrderClient;

@Component
public class RestOrderClient implements OrderClient {
    private final RestClient client;

    public RestOrderClient(
        RestClient.Builder builder,
        @Value("${services.order.url}") String orderServiceUrl) {
        this.client = builder.baseUrl(orderServiceUrl + "/api/orders").build();
    }

    @Override
    public void placeOrder(UUID orderId, UUID userId, Map<UUID, Integer> items) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeOrder'");
    }

}
