package at.fhv.user.application.handler;

import at.fhv.user.application.query.GetUserById;
import at.fhv.user.application.query.GetUserDetailById;
import at.fhv.user.application.query.GetUserOrdersById;
import at.fhv.user.projection.User;
import at.fhv.user.projection.UserDetail;
import at.fhv.user.projection.UserOrder;

import java.util.List;

public interface UserQueryHandler {
    User get(GetUserById query);

    UserDetail getDetail(GetUserDetailById query);

    List<UserOrder> getOrders(GetUserOrdersById query);
}