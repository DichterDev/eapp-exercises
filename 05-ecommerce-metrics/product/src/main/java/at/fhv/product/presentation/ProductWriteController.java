package at.fhv.product.presentation;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.product.application.command.ChangeProductPrice;
import at.fhv.product.application.command.CreateProduct;
import at.fhv.product.application.command.IncreaseProductStock;
import at.fhv.product.application.command.ReduceProductStock;
import at.fhv.product.application.handler.ProductCommandHandler;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductWriteController {
    private final ProductCommandHandler command;

    @PostMapping("/create")
    public ResponseEntity<CommandResponse> create(@RequestBody CreateProduct req) {
        var res = command.create(req);

        return ResponseEntity.status(201).body(res);
    }

    @PostMapping("/{id}/price")
    public ResponseEntity<Void> updatePrice(@PathVariable UUID id, @RequestBody Double price) {
        command.updatePrice(new ChangeProductPrice(id, price));
        return ResponseEntity.status(202).build();
    }

    @PostMapping("/{id}/stock/increase")
    public ResponseEntity<Void> increaseStock(@PathVariable UUID id, @RequestBody Integer amount) {
        command.increaseStock(new IncreaseProductStock(id, amount));
        return ResponseEntity.status(202).build();
    }
}
