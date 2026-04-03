package at.fhv.order.application.client;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products/prices")
    Map<UUID, Double> getPrices(@RequestBody List<UUID> productIds);
}
