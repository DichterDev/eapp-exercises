package at.fhv.ecommerce.order.read.application.handler;

import java.util.List;
import at.fhv.ecommerce.order.read.application.query.GetOrderById;
import at.fhv.ecommerce.order.read.application.query.GetOrderDetailById;
import at.fhv.ecommerce.order.read.application.query.GetOrdersByUserId;
import at.fhv.ecommerce.order.read.projection.Order;
import at.fhv.ecommerce.order.read.projection.OrderDetail;

public interface OrderQueryHandler {
    Order get(GetOrderById query);

    OrderDetail getDetail(GetOrderDetailById query);

    List<OrderDetail> getOrders(GetOrdersByUserId query);
}
