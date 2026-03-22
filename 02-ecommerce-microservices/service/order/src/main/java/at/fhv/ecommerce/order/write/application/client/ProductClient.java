package at.fhv.ecommerce.order.write.application.client;

import java.util.UUID;

public interface ProductClient {
    void reduceStock(String productId, Integer amount);

    Double getPrice(String productId);
}
