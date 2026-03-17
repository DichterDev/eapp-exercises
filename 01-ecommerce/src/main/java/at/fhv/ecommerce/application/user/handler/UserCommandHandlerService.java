package at.fhv.ecommerce.application.user.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.user.command.AddItemToUserCartCommand;
import at.fhv.ecommerce.application.user.command.CheckoutUserCartCommand;
import at.fhv.ecommerce.application.user.command.CompleteUserCartCheckoutCommand;
import at.fhv.ecommerce.application.user.command.RegisterUserCommand;
import at.fhv.ecommerce.domain.order.model.OrderId;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.user.model.CartItem;
import at.fhv.ecommerce.domain.user.model.User;
import at.fhv.ecommerce.domain.user.model.UserId;
import at.fhv.ecommerce.domain.user.ports.UserEventPublisher;
import at.fhv.ecommerce.domain.user.ports.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandHandlerService implements UserCommandHandler {

    private final UserRepository repository;
    private final UserEventPublisher publisher;

    @Override
    @Transactional
    public CommandResponse handleRegister(RegisterUserCommand cmd) {
        var user = User.register(new UserId(cmd.userId()), cmd.name());

        repository.save(user);

        publisher.publish(user.pullEvents());

        return new CommandResponse(user.getId().value().toString());
    }

    @Override
    @Transactional
    public void handleAddItem(AddItemToUserCartCommand cmd) {
        var user = repository.findById(new UserId(cmd.userId())).orElseThrow();

        user.addCartItem(new CartItem(new ProductId(cmd.productId()), cmd.amount()));

        repository.save(user);

        publisher.publish(user.pullEvents());
    }

    @Override
    @Transactional
    public CommandResponse handleCartCheckout(CheckoutUserCartCommand cmd) {
        var user = repository.findById(new UserId(cmd.userId())).orElseThrow();

        user.checkoutCart(new OrderId(cmd.orderId()));

        publisher.publish(user.pullEvents());

        return new CommandResponse(cmd.orderId().toString());
    }

    @Override
    @Transactional
    public void handleCompleteCartCheckout(CompleteUserCartCheckoutCommand cmd) {
        var user = repository.findById(new UserId(cmd.userId())).orElseThrow();

        user.completeCartCheckout();

        repository.save(user);

        publisher.publish(user.pullEvents());
    }
}
