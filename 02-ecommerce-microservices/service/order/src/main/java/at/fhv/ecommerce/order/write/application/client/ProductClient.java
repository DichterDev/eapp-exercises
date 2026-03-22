package at.fhv.ecommerce.order.write.application.client;

import java.util.UUID;

public interface ProductClient {
    void reduceStock(UUID productId, Integer amount);

    Double getPrice(UUID productId);
}
