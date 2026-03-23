package at.fhv.ecommerce.product.presentation.controller;

import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.product.presentation.response.ProductIdResponse;
import at.fhv.ecommerce.product.write.application.command.CreateProduct;
import at.fhv.ecommerce.product.write.application.command.ReduceProductStock;
import at.fhv.ecommerce.product.write.application.handler.ProductCommandHandler;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductWrite {
    private final ProductCommandHandler handler;

    @PostMapping("/create")
    public ResponseEntity<ProductIdResponse> create(@RequestBody CreateProduct req) {
        var res = handler.create(req);

        return ResponseEntity.ok(new ProductIdResponse(res.id().toString()));
    }

    @PostMapping("/{id}/reduce-stock")
    public ResponseEntity<Void> reduceStock(@PathVariable UUID id, @RequestBody Integer amount) {
        handler.reduceStock(new ReduceProductStock(id, amount));
        return ResponseEntity.ok().build();
    }

}
