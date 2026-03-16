package at.fhv.ecommerce.application.user.handler;

import java.util.UUID;
import at.fhv.ecommerce.application.user.query.GetUserByIdQuery;
import at.fhv.ecommerce.application.user.query.GetUserByIdWithCartQuery;
import at.fhv.ecommerce.application.user.view.UserDetailView;
import at.fhv.ecommerce.application.user.view.UserView;

public interface UserQueryHandler {
    UserView handleGet(GetUserByIdQuery query);

    UserDetailView handleGetWithCart(GetUserByIdWithCartQuery query);
}
