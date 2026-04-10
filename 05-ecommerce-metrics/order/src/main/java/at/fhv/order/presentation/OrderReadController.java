package at.fhv.order.presentation;

import at.fhv.common.application.client.response.OrderItemResponse;
import at.fhv.common.application.client.response.OrderResponse;
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

    @GetMapping("/{id}/detail")
    public OrderDetail getDetail(@PathVariable UUID id) {
        return query.getDetail(new GetOrderDetailById(id));
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getByUserId(@PathVariable UUID userId) {
        var orders = query.getOrders(new GetOrdersByUserId(userId));

        return orders.stream()
            .map(
                order -> new OrderResponse(
                    order.orderId(),
                    order.items()
                        .stream()
                        .map(
                            item -> new OrderItemResponse(
                                item.productId(),
                                item.amount(),
                                item.lineTotal()
                            )
                        )
                        .toList(),
                    order.status()
                )
            )
            .toList();
    }

}
