package at.fhv.ecommerce.application.order.handler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.order.command.CancelOrderCommand;
import at.fhv.ecommerce.application.order.command.CompleteOrderCommand;
import at.fhv.ecommerce.application.order.command.FailOrderCommand;
import at.fhv.ecommerce.application.order.command.PlaceOrderCommand;
import at.fhv.ecommerce.domain.order.model.Order;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.order.ports.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCommandHandlerService implements OrderCommandHandler {

    private final OrderRepository repository;

    @Override
    @Transactional
    public CommandResponse handlePlace(PlaceOrderCommand cmd) {
        var order = Order.place(cmd.userId());

        cmd.items().forEach((productId, amount) -> {
            order.addItem(productId, amount);
        });

        repository.save(order);

        return new CommandResponse(order.getId().value().toString());
    }

    @Override
    public void handleComplete(CompleteOrderCommand cmd) {
        var order = repository.findById(cmd.orderId()).orElseThrow();

        order.complete();

        repository.save(order);
    }

    @Override
    public void handleFail(FailOrderCommand cmd) {
        var order = repository.findById(cmd.orderId()).orElseThrow();

        order.fail();

        repository.save(order);
    }

    @Override
    public void handleCancel(CancelOrderCommand cmd) {
        var order = repository.findById(cmd.orderId()).orElseThrow();

        order.cancel();

        repository.save(order);
    }

}
