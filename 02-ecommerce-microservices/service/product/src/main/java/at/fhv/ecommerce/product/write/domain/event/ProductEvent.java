package at.fhv.ecommerce.product.write.domain.event;

import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.product.write.domain.model.ProductId;

public interface ProductEvent extends DomainEvent {
    ProductId productId();
}
