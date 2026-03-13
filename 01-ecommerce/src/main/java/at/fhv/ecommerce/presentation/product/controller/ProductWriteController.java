package at.fhv.ecommerce.presentation.product.controller;

import java.math.BigDecimal;
import java.util.Currency;
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
        var res = command.handleCreate(
            new CreateProductCommand(
                req.name(),
                req.description(),
                new Money(BigDecimal.valueOf(req.price()), Currency.getInstance(req.currency())),
                req.stock()
            )
        );

        return ResponseEntity.ok(mapper.toResponse(res));
    }

}
