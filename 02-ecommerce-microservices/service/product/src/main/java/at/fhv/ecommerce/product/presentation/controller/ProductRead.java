package at.fhv.ecommerce.product.presentation.controller;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.product.read.application.handler.ProductQueryHandler;
import at.fhv.ecommerce.product.read.application.query.GetProductById;
import at.fhv.ecommerce.product.read.application.query.GetProductDetailById;
import at.fhv.ecommerce.product.read.projection.Product;
import at.fhv.ecommerce.product.read.projection.ProductDetail;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRead {
    private final ProductQueryHandler handler;

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable UUID id) {
        var res = handler.get(new GetProductById(id));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<ProductDetail> getDetail(@PathVariable UUID id) {
        var res = handler.getDetail(new GetProductDetailById(id));
        return ResponseEntity.ok(res);
    }
}
