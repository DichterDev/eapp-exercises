package at.fhv.ecommerce.presentation.product.controller;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.application.product.command.CreateProductCommand;
import at.fhv.ecommerce.application.product.handler.ProductCommandHandler;
import at.fhv.ecommerce.domain.common.Money;
import at.fhv.ecommerce.presentation.product.mapper.ProductResponseMapper;
import at.fhv.ecommerce.presentation.product.request.CreateProductRequest;
import at.fhv.ecommerce.presentation.product.response.ProductIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductWriteController {
    private final ProductCommandHandler command;
    private final ProductResponseMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<ProductIdResponse> postMethodName(@RequestBody CreateProductRequest req) {
        UUID uuid = UUID.randomUUID();
        var res = command.handleCreate(
            new CreateProductCommand(
                uuid,
                req.name(),
                req.description(),
                BigDecimal.valueOf(req.price()),
                req.stock()
            )
        );

        return ResponseEntity.ok(mapper.toResponse(res));
    }

}
