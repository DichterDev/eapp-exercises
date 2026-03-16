package at.fhv.ecommerce.application.order.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.application.order.command.CompleteOrderCommand;
import at.fhv.ecommerce.application.order.command.FailOrderCommand;
import at.fhv.ecommerce.application.order.query.GetOrderByIdWithItemsQuery;
import at.fhv.ecommerce.application.product.command.ReduceProductStockCommand;
import at.fhv.ecommerce.application.product.handler.ProductCommandHandler;
import at.fhv.ecommerce.domain.order.events.OrderPlacedEvent;
import at.fhv.ecommerce.domain.product.model.ProductId;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProcessHandlerService implements OrderProcessHandler {

    private final OrderQueryHandler query;
    private final OrderCommandHandler command;
    private final ProductCommandHandler product;

    @Override
    @EventListener
    public void handle(OrderPlacedEvent event) {
        var order = query.handleGetWithItems(
            new GetOrderByIdWithItemsQuery(event.orderId().value())
        );

        System.out.println(order.toString());

        try {
            order.items().forEach(view -> {
                product.handleReduceStock(
                    new ReduceProductStockCommand(
                        new ProductId(view.productId()),
                        view.amount()
                    )
                );
            });

            command.handleComplete(new CompleteOrderCommand(event.orderId()));

        } catch (Exception ex) {
            command.handleFail(new FailOrderCommand(event.orderId()));
        }
    }
}
