package at.fhv.ecommerce.order.write.application.handler;

import org.springframework.stereotype.Service;
import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.order.write.application.command.PlaceOrder;
import at.fhv.ecommerce.order.write.domain.model.Order;
import at.fhv.ecommerce.order.write.domain.model.OrderId;
import at.fhv.ecommerce.order.write.domain.model.ProductId;
import at.fhv.ecommerce.order.write.domain.model.UserId;
import at.fhv.ecommerce.order.write.domain.port.OrderEventPublisher;
import at.fhv.ecommerce.order.write.domain.port.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCommandHandlerService implements OrderCommandHandler {
    private final OrderRepository repository;
    private final OrderEventPublisher publisher;

    @Override
    @Transactional
    public CommandResponse place(PlaceOrder cmd) {
        var order = Order.place(
            new OrderId(cmd.orderId()),
            new UserId(cmd.userId())
        );

        cmd.orderItems().forEach((pId, amount) -> {
            order.addItem(new ProductId(pId), amount);
        });

        repository.save(order);

        publisher.publishAll(order.pullEvents());

        return new CommandResponse(order.getId().value().toString());
    }
}
