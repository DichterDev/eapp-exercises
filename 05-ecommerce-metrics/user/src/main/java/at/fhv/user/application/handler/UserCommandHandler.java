package at.fhv.user.application.handler;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.user.application.command.*;

public interface UserCommandHandler {
    CommandResponse register(RegisterUser cmd);

    void rename(ChangeUserName cmd);

    void addItem(AddUserCartItem cmd);

    CommandResponse checkout(CheckoutUserCart cmd);

    void completeCartCheckout(CompleteUserCartCheckout cmd);
}
