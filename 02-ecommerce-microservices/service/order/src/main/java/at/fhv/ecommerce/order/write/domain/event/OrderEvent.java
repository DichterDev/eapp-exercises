package at.fhv.ecommerce.order.write.domain.event;

import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.order.write.domain.model.OrderId;

public interface OrderEvent extends DomainEvent {
    OrderId orderId();
}
