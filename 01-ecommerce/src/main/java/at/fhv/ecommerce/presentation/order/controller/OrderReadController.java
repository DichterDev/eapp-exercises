package at.fhv.ecommerce.presentation.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.fhv.ecommerce.application.order.handler.OrderQueryHandler;
import at.fhv.ecommerce.application.order.query.GetOrderByIdQuery;
import at.fhv.ecommerce.application.order.query.GetOrderByIdWithItemsQuery;
import at.fhv.ecommerce.presentation.order.mapper.OrderResponseMapper;
import at.fhv.ecommerce.presentation.order.response.OrderDetailResponse;
import at.fhv.ecommerce.presentation.order.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderReadController {
    private final OrderQueryHandler query;
    private final OrderResponseMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> get(@PathVariable UUID id) {
        var view = query.handleGet(new GetOrderByIdQuery(id));

        return ResponseEntity.ok(mapper.toResponse(view));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<OrderDetailResponse> getDetail(@PathVariable UUID id) {
        var view = query.handleGetWithItems(new GetOrderByIdWithItemsQuery(id));

        return ResponseEntity.ok(mapper.toResponse(view));
    }

}
