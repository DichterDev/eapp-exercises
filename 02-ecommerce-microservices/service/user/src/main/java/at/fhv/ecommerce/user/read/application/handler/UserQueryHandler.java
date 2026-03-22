package at.fhv.ecommerce.user.read.application.handler;

import java.util.List;
import at.fhv.ecommerce.user.read.application.query.GetUserById;
import at.fhv.ecommerce.user.read.application.query.GetUserDetailById;
import at.fhv.ecommerce.user.read.application.query.GetUserOrdersById;
import at.fhv.ecommerce.user.read.projection.User;
import at.fhv.ecommerce.user.read.projection.UserDetail;
import at.fhv.ecommerce.user.read.projection.UserOrder;

public interface UserQueryHandler {
    User get(GetUserById query);

    UserDetail getDetail(GetUserDetailById query);

    List<UserOrder> getOrders(GetUserOrdersById query);
}
