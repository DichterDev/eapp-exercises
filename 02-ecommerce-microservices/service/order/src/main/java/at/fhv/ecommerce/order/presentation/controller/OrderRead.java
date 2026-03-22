package at.fhv.ecommerce.order.presentation.controller;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.order.read.application.handler.OrderQueryHandler;
import at.fhv.ecommerce.order.read.application.query.GetOrderDetailById;
import at.fhv.ecommerce.order.read.projection.OrderDetail;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRead {
    private final OrderQueryHandler handler;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> get(@PathVariable UUID id) {
        var res = handler.getDetail(new GetOrderDetailById(id));

        return ResponseEntity.ok(res);
    }
}
