package at.fhv.order.application.client;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("product")
public interface ProductClient {
    @PostMapping("/api/products/q/prices")
    Map<UUID, Double> getPrices(@RequestBody List<UUID> productIds);
}
