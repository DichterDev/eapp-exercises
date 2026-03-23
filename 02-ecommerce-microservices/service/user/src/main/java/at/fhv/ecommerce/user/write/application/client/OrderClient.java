package at.fhv.ecommerce.user.write.application.client;

import java.util.Map;
import java.util.UUID;

public interface OrderClient {
    void placeOrder(UUID orderId, UUID userId, Map<UUID, Integer> items);
}
