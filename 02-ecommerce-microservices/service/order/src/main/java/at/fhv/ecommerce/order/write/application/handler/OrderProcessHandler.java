package at.fhv.ecommerce.order.write.application.handler;

import at.fhv.ecommerce.order.write.domain.event.OrderPlaced;

public interface OrderProcessHandler {
    void handle(OrderPlaced event);
}
