package at.fhv.ecommerce.order.write.application.handler;

import org.springframework.stereotype.Service;
import at.fhv.ecommerce.order.read.application.handler.OrderQueryHandler;
import at.fhv.ecommerce.order.read.application.query.GetOrderById;
import at.fhv.ecommerce.order.read.application.query.GetOrderDetailById;
import at.fhv.ecommerce.order.write.application.client.ProductClient;
import at.fhv.ecommerce.order.write.domain.event.OrderPlaced;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProcessHandlerService implements OrderProcessHandler {
    private final ProductClient product;
    private final OrderQueryHandler query;

    @Override
    public void handle(OrderPlaced event) {
        var order = query.getDetail(new GetOrderDetailById(event.orderId().value()));

        order.orderItems().forEach(item -> {
            product.reduceStock(item.productId(), item.amount());
        });
    }

}
