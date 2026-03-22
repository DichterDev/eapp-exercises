package at.fhv.ecommerce.order.write.application.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.order.read.application.handler.OrderQueryHandler;
import at.fhv.ecommerce.order.read.application.query.GetOrderDetailById;
import at.fhv.ecommerce.order.write.application.client.ProductClient;
import at.fhv.ecommerce.order.write.application.command.CompleteOrder;
import at.fhv.ecommerce.order.write.application.command.FailOrder;
import at.fhv.ecommerce.order.write.domain.event.OrderPlaced;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProcessHandlerService implements OrderProcessHandler {
    private final ProductClient product;
    private final OrderQueryHandler query;
    private final OrderCommandHandler command;

    @Override
    @EventListener
    public void handle(OrderPlaced event) {
        var order = query.getDetail(new GetOrderDetailById(event.orderId().value()));

        try {
            order.orderItems().forEach(item -> {
                product.reduceStock(item.productId(), item.amount());
            });

            command.complete(new CompleteOrder(event.orderId().value()));

        } catch (Exception ex) {
            command.fail(new FailOrder(event.orderId().value()));
        }
    }
}
