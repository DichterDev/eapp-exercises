package at.fhv.user.application.handler;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.common.domain.model.Money;
import at.fhv.user.application.client.ProductClient;
import at.fhv.user.application.command.*;
import at.fhv.user.domain.model.ProductId;
import at.fhv.user.domain.model.User;
import at.fhv.user.domain.model.UserId;
import at.fhv.user.domain.port.UserEventPublisher;
import at.fhv.user.domain.port.UserWriteRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandHandlerService implements UserCommandHandler {
    private final UserWriteRepository repository;
    private final UserEventPublisher publisher;
    private final ProductClient product;

    private final MeterRegistry meter;

    private User get(UUID id) {
        return repository.findById(new UserId(id)).orElseThrow();
    }

    @Override
    public CommandResponse register(RegisterUser cmd) {
        var user = User.register(new UserId(cmd.userId()), cmd.name());

        repository.save(user);

        publisher.publishAll(user.pullEvents());

        meter.counter("user.register.total").increment();

        return new CommandResponse(user.getId().value().toString());
    }

    @Override
    public void rename(ChangeUserName cmd) {
        var user = get(cmd.userId());

        user.rename(cmd.newName());

        repository.save(user);

        meter.counter("user.rename.total").increment();

        publisher.publishAll(user.pullEvents());
    }

    @Override
    public void addItem(AddUserCartItem cmd) {
        var user = get(cmd.userId());

        user.addCartItem(
            new ProductId(cmd.productId()),
            cmd.amount()
        );

        repository.save(user);

        meter.counter(
            "user.cart.add.total",
            List.of(
                Tag.of("user_id", user.getId().value().toString()),
                Tag.of("user_name", user.getName())
            )
        ).increment();

        publisher.publishAll(user.pullEvents());
    }

    @Override
    public CommandResponse checkout(CheckoutUserCart cmd) {
        var user = get(cmd.userId());

        if (user.getCart() == null || user.getCart().isEmpty()) {
            throw new NullPointerException();
        }

        UUID orderId = user.checkout();

        repository.save(user);

        meter.counter(
            "user.cart.checkout.total",
            List.of(
                Tag.of("user_id", user.getId().value().toString()),
                Tag.of("user_name", user.getName())
            )
        ).increment();

        publisher.publishAll(user.pullEvents());

        return new CommandResponse(orderId.toString());
    }

    @Override
    public void completeCartCheckout(CompleteUserCartCheckout cmd) {
        var user = get(cmd.userId());

        user.completeCheckout();

        repository.save(user);

        meter.counter(
            "user.cart.complete.total",
            List.of(
                Tag.of("user_id", user.getId().value().toString()),
                Tag.of("user_name", user.getName())
            )
        ).increment();

        publisher.publishAll(user.pullEvents());
    }
}
