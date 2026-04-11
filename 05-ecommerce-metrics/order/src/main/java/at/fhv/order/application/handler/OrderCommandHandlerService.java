package at.fhv.order.application.handler;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.common.domain.model.Money;
import at.fhv.order.application.client.ProductClient;
import at.fhv.order.application.command.*;
import at.fhv.order.domain.model.Order;
import at.fhv.order.domain.model.OrderId;
import at.fhv.order.domain.model.OrderItemDTO;
import at.fhv.order.domain.model.ProductId;
import at.fhv.order.domain.model.UserId;
import at.fhv.order.domain.port.OrderEventPublisher;
import at.fhv.order.domain.port.OrderWriteRepository;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderCommandHandlerService implements OrderCommandHandler {
    private final OrderWriteRepository repository;
    private final OrderEventPublisher publisher;
    private final ProductClient product;

    private final MeterRegistry meter;

    private Order get(UUID id) {
        return repository.findById(new OrderId(id)).orElseThrow();
    }

    @Override
    public CommandResponse place(PlaceOrder cmd) {

        if (repository.findById(new OrderId(cmd.orderId())).isPresent()) {
            return new CommandResponse(cmd.orderId().toString());
        }

        var prices = product.getPrices(cmd.orderItems().keySet().stream().toList());

        List<OrderItemDTO> items = new ArrayList<>();

        prices.forEach(
            (id, price) -> items.add(
                new OrderItemDTO(
                    new ProductId(id),
                    cmd.orderItems().get(id),
                    new Money(BigDecimal.valueOf(price))
                )
            )
        );

        var order = Order.place(
            new OrderId(cmd.orderId()),
            new UserId(cmd.userId()),
            items
        );

        repository.save(order);

        publisher.publishAll(order.pullEvents());

        meter.counter("order.placed.total").increment();

        return new CommandResponse(order.getId().value().toString());
    }

    @Override
    public void complete(CompleteOrder cmd) {
        var order = get(cmd.orderId());

        order.complete();

        repository.save(order);

        meter.counter("order.completed.total").increment();

        publisher.publishAll(order.pullEvents());
    }

    @Override
    public void fail(FailOrder cmd) {
        var order = get(cmd.orderId());

        order.fail();

        repository.save(order);

        meter.counter("order.failed.total").increment();

        publisher.publishAll(order.pullEvents());
    }

    @Override
    public void cancel(CancelOrder cmd) {
        var order = get(cmd.orderId());

        order.cancel();

        repository.save(order);

        meter.counter("order.cancelled.total").increment();

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
