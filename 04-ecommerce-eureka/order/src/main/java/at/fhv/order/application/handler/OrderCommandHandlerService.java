package at.fhv.order.application.handler;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.order.application.command.*;
import at.fhv.order.domain.model.Order;
import at.fhv.order.domain.model.OrderId;
import at.fhv.order.domain.model.UserId;
import at.fhv.order.domain.port.OrderEventPublisher;
import at.fhv.order.domain.port.OrderWriteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderCommandHandlerService implements OrderCommandHandler {
    private final OrderWriteRepository repository;
    private final OrderEventPublisher publisher;

    private Order get(UUID id) {
        return repository.findById(new OrderId(id)).orElseThrow();
    }

    @Override
    public CommandResponse place(PlaceOrder cmd) {

        if (repository.findById(new OrderId(cmd.orderId())).isPresent()) {
            return new CommandResponse(cmd.orderId().toString());
        }

        var order = Order.place(
                new OrderId(cmd.orderId()),
                new UserId(cmd.userId()),
                cmd.orderItems()
        );

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

        order.fail();

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

    @Override
    public void registerReducedStock(RegisterReducedStock cmd) {
        var order = get(cmd.orderId());

        order.handleReducedStock();

        repository.save(order);

        publisher.publishAll(order.pullEvents());
    }
}