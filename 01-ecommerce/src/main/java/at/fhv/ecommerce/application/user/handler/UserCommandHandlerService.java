package at.fhv.ecommerce.application.user.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.user.command.AddItemToUserCartCommand;
import at.fhv.ecommerce.application.user.command.RegisterUserCommand;
import at.fhv.ecommerce.domain.user.model.CartItem;
import at.fhv.ecommerce.domain.user.model.User;
import at.fhv.ecommerce.domain.user.ports.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandHandlerService implements UserCommandHandler {

    private final UserRepository repository;

    @Override
    @Transactional
    public CommandResponse handleRegister(RegisterUserCommand cmd) {
        var user = User.register(cmd.name());

        repository.save(user);

        return new CommandResponse(user.getId().value().toString());
    }

    @Override
    @Transactional
    public void handleAddItem(AddItemToUserCartCommand cmd) {
        var user = repository.findById(cmd.userId()).orElseThrow();

        user.addCartItem(new CartItem(cmd.productId(), cmd.amount()));

        repository.save(user);
    }
}
