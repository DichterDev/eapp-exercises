package at.fhv.ecommerce.application.order.handler;

import java.util.List;
import at.fhv.ecommerce.application.order.query.GetOrderByIdQuery;
import at.fhv.ecommerce.application.order.query.GetOrderByIdWithItemsQuery;
import at.fhv.ecommerce.application.order.query.GetOrdersByUserIdWithItemsQuery;
import at.fhv.ecommerce.application.order.view.OrderDetailsView;
import at.fhv.ecommerce.application.order.view.OrderView;

public interface OrderQueryHandler {
    OrderView handleGet(GetOrderByIdQuery query);

    OrderDetailsView handleGetWithItems(GetOrderByIdWithItemsQuery query);

    List<OrderDetailsView> handleGetByUserId(GetOrdersByUserIdWithItemsQuery query);
}
