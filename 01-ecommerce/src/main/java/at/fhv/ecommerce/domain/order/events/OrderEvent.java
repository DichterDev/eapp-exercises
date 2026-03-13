package at.fhv.ecommerce.domain.order.events;

import at.fhv.ecommerce.domain.common.DomainEvent;
import at.fhv.ecommerce.domain.order.model.OrderId;

public interface OrderEvent extends DomainEvent {
    OrderId orderId();
}
