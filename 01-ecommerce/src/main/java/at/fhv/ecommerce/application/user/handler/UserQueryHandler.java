package at.fhv.ecommerce.application.user.handler;

import java.util.List;
import java.util.UUID;
import at.fhv.ecommerce.application.user.query.GetUserByIdQuery;
import at.fhv.ecommerce.application.user.query.GetUserByIdWithCartQuery;
import at.fhv.ecommerce.application.user.query.GetUserOrdersById;
import at.fhv.ecommerce.application.user.query.GetUsersQuery;
import at.fhv.ecommerce.application.user.query.GetUsersWithCartQuery;
import at.fhv.ecommerce.application.user.view.UserDetailView;
import at.fhv.ecommerce.application.user.view.UserOrderView;
import at.fhv.ecommerce.application.user.view.UserView;

public interface UserQueryHandler {
    UserView handleGet(GetUserByIdQuery query);

    UserDetailView handleGetWithCart(GetUserByIdWithCartQuery query);

    List<UserOrderView> handleGetOrders(GetUserOrdersById query);

    List<UserView> handleGetAll(GetUsersQuery query);

    List<UserDetailView> handleGetAllDetail(GetUsersWithCartQuery query);
}
