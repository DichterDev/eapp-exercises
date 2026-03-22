package at.fhv.ecommerce.order.presentation.controller;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.order.presentation.request.PlaceOrderRequest;
import at.fhv.ecommerce.order.write.application.command.CancelOrder;
import at.fhv.ecommerce.order.write.application.command.CompleteOrder;
import at.fhv.ecommerce.order.write.application.command.FailOrder;
import at.fhv.ecommerce.order.write.application.command.PlaceOrder;
import at.fhv.ecommerce.order.write.application.handler.OrderCommandHandler;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderWrite {
    private final OrderCommandHandler handler;

    @PostMapping("/place")
    public ResponseEntity<CommandResponse> place(@RequestBody PlaceOrderRequest req) {
        var oId = UUID.fromString(req.orderId());
        var uId = UUID.fromString(req.userId());

        var res = handler.place(
            new PlaceOrder(
                oId,
                uId,
                req.items()
                    .entrySet()
                    .stream()
                    .collect(
                        Collectors.toMap(
                            entry -> UUID.fromString(entry.getKey()),
                            Map.Entry::getValue
                        )
                    )
            )
        );

        return ResponseEntity.ok(res);
    }

    @PostMapping("/{orderId}/complete")
    public ResponseEntity<Void> complete(@PathVariable UUID orderId) {
        handler.complete(new CompleteOrder(orderId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{orderId}/fail")
    public ResponseEntity<Void> fail(@PathVariable UUID orderId) {
        handler.fail(new FailOrder(orderId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable UUID orderId) {
        handler.cancel(new CancelOrder(orderId));
        return ResponseEntity.ok().build();
    }
}
