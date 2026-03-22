package at.fhv.ecommerce.order.write.application.handler;

import java.util.UUID;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.order.write.application.command.CancelOrder;
import at.fhv.ecommerce.order.write.application.command.CompleteOrder;
import at.fhv.ecommerce.order.write.application.command.FailOrder;
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

    private Order get(UUID id) {
        return repository.findById(new OrderId(id)).orElseThrow();
    }

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

    @Override
    public void complete(CompleteOrder cmd) {
        var order = get(cmd.orderId());

        order.complete();

        repository.save(order);

        publisher.publishAll(order.pullEvents());
    }

    @Override
    public void fail(FailOrder cmd) {
        var order = get(cmd.orderId());

        order.cancel();

        repository.save(order);

        publisher.publishAll(order.pullEvents());
    }

    @Override
    public void cancel(CancelOrder cmd) {
        var order = get(cmd.orderId());

        order.cancel();

        repository.save(order);

        publisher.publishAll(order.pullEvents());
    }
}
