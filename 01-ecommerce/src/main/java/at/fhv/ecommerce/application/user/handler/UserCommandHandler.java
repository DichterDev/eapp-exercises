package at.fhv.ecommerce.application.user.handler;

import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.user.command.AddItemToUserCartCommand;
import at.fhv.ecommerce.application.user.command.CheckoutUserCartCommand;
import at.fhv.ecommerce.application.user.command.CompleteUserCartCheckoutCommand;
import at.fhv.ecommerce.application.user.command.RegisterUserCommand;

public interface UserCommandHandler {
    CommandResponse handleRegister(RegisterUserCommand cmd);

    void handleAddItem(AddItemToUserCartCommand cmd);

    CommandResponse handleCartCheckout(CheckoutUserCartCommand cmd);

    void handleCompleteCartCheckout(CompleteUserCartCheckoutCommand cmd);
}
