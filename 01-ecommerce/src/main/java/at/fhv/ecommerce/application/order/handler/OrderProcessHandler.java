package at.fhv.ecommerce.application.order.handler;

import at.fhv.ecommerce.domain.order.events.OrderPlacedEvent;

public interface OrderProcessHandler {
    void handle(OrderPlacedEvent event);
}
