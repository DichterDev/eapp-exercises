package at.fhv.common.domain.event.product;

import at.fhv.common.domain.event.DomainEvent;

import java.util.UUID;

public interface ProductEvent extends DomainEvent {

    UUID productId();
}
