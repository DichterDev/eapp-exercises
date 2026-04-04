package at.fhv.order.application.handler;

import at.fhv.order.application.query.GetOrderById;
import at.fhv.order.application.query.GetOrderDetailById;
import at.fhv.order.application.query.GetOrdersByUserId;
import at.fhv.order.projection.Order;
import at.fhv.order.projection.OrderDetail;

import java.util.List;

public interface OrderQueryHandler {
    Order get(GetOrderById query);

    OrderDetail getDetail(GetOrderDetailById query);

    List<OrderDetail> getOrders(GetOrdersByUserId query);
}
