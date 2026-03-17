package at.fhv.ecommerce.presentation.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.application.product.handler.ProductQueryHandler;
import at.fhv.ecommerce.application.product.query.GetProductByIdQuery;
import at.fhv.ecommerce.presentation.product.mapper.ProductResponseMapper;
import at.fhv.ecommerce.presentation.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductReadController {
    private final ProductQueryHandler query;
    private final ProductResponseMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id) {
        var view = query.handleGet(new GetProductByIdQuery(id));

        return ResponseEntity.ok(mapper.toResponse(view));
    }

}
