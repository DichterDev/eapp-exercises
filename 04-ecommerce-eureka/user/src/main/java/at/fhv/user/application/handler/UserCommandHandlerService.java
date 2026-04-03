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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandHandlerService implements UserCommandHandler {
    private final UserWriteRepository repository;
    private final UserEventPublisher publisher;
    private final ProductClient product;

    private User get(UUID id) {
        return repository.findById(new UserId(id)).orElseThrow();
    }

    @Override
    public CommandResponse register(RegisterUser cmd) {
        var user = User.register(new UserId(cmd.userId()), cmd.name());

        repository.save(user);

        publisher.publishAll(user.pullEvents());

        return new CommandResponse(user.getId().value().toString());
    }

    @Override
    public void rename(ChangeUserName cmd) {
        var user = get(cmd.userId());

        user.rename(cmd.newName());

        repository.save(user);

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
        publisher.publishAll(user.pullEvents());
    }

    @Override
    public void checkout(CheckoutUserCart cmd) {
        var user = get(cmd.userId());

        if (user.getCart() == null || user.getCart().isEmpty()) {
            return;
        }

        user.checkout();

        repository.save(user);
        publisher.publishAll(user.pullEvents());
    }

    @Override
    public void completeCartCheckout(CompleteUserCartCheckout cmd) {
        var user = get(cmd.userId());

        user.completeCheckout();
    }
}
