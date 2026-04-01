package at.fhv.order.presentation;

import at.fhv.order.application.handler.OrderQueryHandler;
import at.fhv.order.application.query.GetOrderById;
import at.fhv.order.application.query.GetOrderDetailById;
import at.fhv.order.application.query.GetOrdersByUserId;
import at.fhv.order.projection.Order;
import at.fhv.order.projection.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/orders/q")
@RequiredArgsConstructor
public class OrderReadController {
    private final OrderQueryHandler query;

    @GetMapping("/{id}")
    public Order get(@PathVariable UUID id) {
        return query.get(new GetOrderById(id));
    }

    @GetMapping("/detail/{id}")
    public OrderDetail getDetail(@PathVariable UUID id) {
        return query.getDetail(new GetOrderDetailById(id));
    }

    @GetMapping
    public List<Order> getByUserId(@RequestParam UUID userId) {
        return query.getOrders(new GetOrdersByUserId(userId));
    }

}
