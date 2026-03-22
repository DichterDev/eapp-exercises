package at.fhv.ecommerce.order.read.infrastructure.persistence;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.order.read.projection.Order;
import at.fhv.ecommerce.order.read.projection.OrderDetail;
import at.fhv.ecommerce.order.read.projection.OrderItem;
import at.fhv.ecommerce.order.read.projection.repository.OrderRepository;
import at.fhv.ecommerce.order.write.infrastructure.persistence.JpaOrderRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringOrderReadRepository implements OrderRepository {
    private final JpaOrderRepository jpa;

    @Override
    public Order findById(UUID orderId) {
        var order = jpa.findById(orderId).orElseThrow();

        return new Order(
            order.getId().toString(),
            order.getUserId().toString(),
            order.getStatus().toString()
        );
    }

    @Override
    public OrderDetail findDetailById(UUID orderId) {
        var order = jpa.findById(orderId).orElseThrow();

        return new OrderDetail(
            order.getId().toString(),
            order.getUserId().toString(),
            order.getStatus().toString(),
            order.getItems().stream().map(item -> {
                return new OrderItem(item.getProductId().toString(), item.getAmount());
            }).toList()
        );
    }

    @Override
    public List<OrderDetail> findByUserId(UUID userId) {
        var orders = jpa.findByUserIdWithItems(userId, PageRequest.of(0, 50));

        return orders.stream().map(order -> {
            return new OrderDetail(
                order.getId().toString(),
                order.getUserId().toString(),
                order.getStatus().toString(),
                order.getItems().stream().map(item -> {
                    return new OrderItem(item.getProductId().toString(), item.getAmount());
                }).toList()
            );
        }).toList();
    }
}
