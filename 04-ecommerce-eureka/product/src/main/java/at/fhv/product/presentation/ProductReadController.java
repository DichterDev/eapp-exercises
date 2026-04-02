package at.fhv.product.presentation;

import at.fhv.common.application.client.response.ProductPriceResponse;
import at.fhv.product.application.handler.ProductQueryHandler;
import at.fhv.product.application.query.GetProductById;

import at.fhv.product.application.query.GetProductDetailById;
import at.fhv.product.projection.Product;
import at.fhv.product.projection.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/products/q")
@RequiredArgsConstructor

public class ProductReadController {

    private final ProductQueryHandler query;

    @GetMapping("/{id}")
    public Product get(@PathVariable UUID id) {
        return query.get(new GetProductById(id));
    }

    @GetMapping("/detail/{id}")
    public ProductDetail getDetail(@PathVariable UUID id) {
        return query.getDetail(new GetProductDetailById(id));
    }

    @GetMapping("/{id}/price")
    public ProductPriceResponse getPrice(@PathVariable UUID id) {
        var product = query.get(new GetProductById(id));

        return new ProductPriceResponse(product.productId(), product.price());
    }
}
