package at.fhv.ecommerce.domain.product.event;

import at.fhv.ecommerce.domain.common.DomainEvent;
import at.fhv.ecommerce.domain.product.model.ProductId;

public interface ProductEvent extends DomainEvent {
    ProductId productId();
}
