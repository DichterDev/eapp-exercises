package at.fhv.ecommerce.application.order.handler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.order.command.CancelOrderCommand;
import at.fhv.ecommerce.application.order.command.CompleteOrderCommand;
import at.fhv.ecommerce.application.order.command.FailOrderCommand;
import at.fhv.ecommerce.application.order.command.PlaceOrderCommand;
import at.fhv.ecommerce.application.product.handler.ProductQueryHandler;
import at.fhv.ecommerce.application.product.query.CheckIfProductExistsQuery;
import at.fhv.ecommerce.domain.order.model.Order;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.order.ports.OrderEventPublisher;
import at.fhv.ecommerce.domain.order.ports.OrderRepository;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.user.model.UserId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCommandHandlerService implements OrderCommandHandler {

    private final OrderRepository repository;
    private final OrderEventPublisher publisher;
    private final ProductQueryHandler product;

    @Override
    @Transactional
    public CommandResponse handlePlace(PlaceOrderCommand cmd) {
        var order = Order.place(new OrderId(cmd.orderId()), new UserId(cmd.userId()));

        cmd.items().forEach((pId, amount) -> {
            if (product.checkIfExists(new CheckIfProductExistsQuery(pId))) {
                order.addItem(new ProductId(pId), amount);
            }
        });

        repository.save(order);

        publisher.publish(order.pullEvents());

        return new CommandResponse(order.getId().value().toString());
    }

    @Override
    public void handleComplete(CompleteOrderCommand cmd) {
        var order = repository.findById(new OrderId(cmd.orderId())).orElseThrow();

        order.complete();

        repository.save(order);

        publisher.publish(order.pullEvents());
    }

    @Override
    public void handleFail(FailOrderCommand cmd) {
        var order = repository.findById(new OrderId(cmd.orderId())).orElseThrow();

        order.fail();

        repository.save(order);

        publisher.publish(order.pullEvents());
    }

    @Override
    public void handleCancel(CancelOrderCommand cmd) {
        var order = repository.findById(new OrderId(cmd.orderId())).orElseThrow();

        order.cancel();

        repository.save(order);

        publisher.publish(order.pullEvents());
    }

}
