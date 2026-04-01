package at.fhv.order.application.handler;

import at.fhv.order.application.query.GetOrderById;
import at.fhv.order.application.query.GetOrderDetailById;
import at.fhv.order.application.query.GetOrdersByUserId;
import at.fhv.order.domain.port.OrderReadRepository;
import at.fhv.order.projection.Order;
import at.fhv.order.projection.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderQueryHandlerService implements OrderQueryHandler {
    private final OrderReadRepository repository;

    @Override
    public Order get(GetOrderById query) {
        return repository.get(query.orderId()).orElseThrow();
    }

    @Override
    public OrderDetail getDetail(GetOrderDetailById query) {
        return repository.getDetail(query.orderId()).orElseThrow();
    }

    @Override
    public List<Order> getOrders(GetOrdersByUserId query) {
        return repository.getByUserId(query.userId());
    }

}