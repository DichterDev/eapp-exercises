package at.fhv.ecommerce.application.user.handler;

import at.fhv.ecommerce.domain.user.event.UserCartCheckedOutEvent;

public interface UserCartProcessHandler {
    void handle(UserCartCheckedOutEvent event);
}
