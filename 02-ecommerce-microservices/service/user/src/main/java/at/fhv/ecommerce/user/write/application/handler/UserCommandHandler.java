package at.fhv.ecommerce.user.write.application.handler;

import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.user.write.application.command.AddUserCartItem;
import at.fhv.ecommerce.user.write.application.command.ChangeUserName;
import at.fhv.ecommerce.user.write.application.command.CheckoutUserCart;
import at.fhv.ecommerce.user.write.application.command.CompleteUserCartCheckout;
import at.fhv.ecommerce.user.write.application.command.RegisterUser;

public interface UserCommandHandler {
    CommandResponse register(RegisterUser cmd);

    void rename(ChangeUserName cmd);

    void addItem(AddUserCartItem cmd);

    CommandResponse checkout(CheckoutUserCart cmd);

    void completeCheckout(CompleteUserCartCheckout cmd);
}
