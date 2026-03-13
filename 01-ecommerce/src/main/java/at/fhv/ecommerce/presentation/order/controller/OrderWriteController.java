package at.fhv.ecommerce.presentation.order.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.application.order.command.PlaceOrderCommand;
import at.fhv.ecommerce.application.order.handler.OrderCommandHandler;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.user.model.UserId;
import at.fhv.ecommerce.presentation.order.mapper.OrderResponseMapper;
import at.fhv.ecommerce.presentation.order.request.PlaceOrderRequest;
import at.fhv.ecommerce.presentation.order.response.OrderIdResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderWriteController {
    private final OrderCommandHandler command;
    private final OrderResponseMapper mapper;

    @PostMapping("/place")
    public ResponseEntity<OrderIdResponse> place(@RequestBody PlaceOrderRequest req) {
        var userUUID = UUID.fromString(req.userId());
        Map<ProductId, Integer> items = new HashMap<>();

        for (Map.Entry<String, Integer> entry : req.items().entrySet()) {
            var uuid = UUID.fromString(entry.getKey());
            items.put(new ProductId(uuid), entry.getValue());
        }

        var res = command.handlePlace(
            new PlaceOrderCommand(
                new UserId(userUUID),
                items
            )
        );

        return ResponseEntity.ok(mapper.toResponse(res));
    }
}
