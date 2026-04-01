package at.fhv.user.application.handler;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.user.application.command.*;

public interface UserCommandHandler {
    CommandResponse register(RegisterUser cmd);

    void rename(ChangeUserName cmd);

    void addItem(AddUserCartItem cmd);

    void checkout(CheckoutUserCart cmd);

    void registerUserOrder(RegisterUserOrder cmd);

    void completeUserOrder(CompleteUserOrder cmd);

    void failUserOrder(FailUserOrder cmd);

    void completeCartCheckout(CompleteUserCartCheckout cmd);
}