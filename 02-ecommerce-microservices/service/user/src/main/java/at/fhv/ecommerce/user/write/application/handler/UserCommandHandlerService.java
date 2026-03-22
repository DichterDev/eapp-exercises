package at.fhv.ecommerce.user.write.application.handler;

import java.util.UUID;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.user.write.application.command.AddUserCartItem;
import at.fhv.ecommerce.user.write.application.command.ChangeUserName;
import at.fhv.ecommerce.user.write.application.command.CheckoutUserCart;
import at.fhv.ecommerce.user.write.application.command.CompleteUserCartCheckout;
import at.fhv.ecommerce.user.write.application.command.RegisterUser;
import at.fhv.ecommerce.user.write.domain.model.ProductId;
import at.fhv.ecommerce.user.write.domain.model.User;
import at.fhv.ecommerce.user.write.domain.model.UserId;
import at.fhv.ecommerce.user.write.domain.port.UserEventPublisher;
import at.fhv.ecommerce.user.write.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandHandlerService implements UserCommandHandler {
    private final UserRepository repository;
    private final UserEventPublisher publisher;

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

        user.addCartItem(new ProductId(cmd.productId()), cmd.amount());

        repository.save(user);

        publisher.publishAll(user.pullEvents());
    }

    @Override
    public void checkout(CheckoutUserCart cmd) {
        var user = get(cmd.userId());

        user.cartCheckout();

        repository.save(user);

        publisher.publishAll(user.pullEvents());
    }

    @Override
    public void completeCheckout(CompleteUserCartCheckout cmd) {
        var user = get(cmd.userId());

        user.cartCompleteCheckout();

        repository.save(user);

        publisher.publishAll(user.pullEvents());
    }

}
