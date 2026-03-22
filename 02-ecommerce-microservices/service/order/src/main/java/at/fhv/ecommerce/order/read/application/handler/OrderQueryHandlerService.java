package at.fhv.ecommerce.order.read.application.handler;

import java.util.List;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.order.read.application.query.GetOrderById;
import at.fhv.ecommerce.order.read.application.query.GetOrderDetailById;
import at.fhv.ecommerce.order.read.application.query.GetOrdersByUserId;
import at.fhv.ecommerce.order.read.projection.Order;
import at.fhv.ecommerce.order.read.projection.OrderDetail;
import at.fhv.ecommerce.order.read.projection.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderQueryHandlerService implements OrderQueryHandler {
    private final OrderRepository repository;

    @Override
    public Order get(GetOrderById query) {
        return repository.findById(query.orderId());
    }

    @Override
    public OrderDetail getDetail(GetOrderDetailById query) {
        return repository.findDetailById(query.orderId());
    }

    @Override
    public List<OrderDetail> getOrders(GetOrdersByUserId query) {
        return repository.findByUserId(query.userId());
    }
}
