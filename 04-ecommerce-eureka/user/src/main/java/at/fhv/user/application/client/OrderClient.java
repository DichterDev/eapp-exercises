package at.fhv.user.application.client;

import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import at.fhv.common.application.client.response.OrderResponse;

@FeignClient(name = "order")
public interface OrderClient {
    @GetMapping("/api/orders/q/{userId}")
    List<OrderResponse> getOrders(@PathVariable UUID userId);
}
