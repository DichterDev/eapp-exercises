package at.fhv.user.application.client;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import at.fhv.common.application.client.response.ProductPriceResponse;

@FeignClient(name = "product-client")
public interface ProductClient {
    @GetMapping("/api/products/q/{productId}/price")
    ProductPriceResponse getPrice(@PathVariable("productId") UUID productId);
}
