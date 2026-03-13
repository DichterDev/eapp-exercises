package at.fhv.ecommerce.domain.user.event;

import at.fhv.ecommerce.domain.common.DomainEvent;
import at.fhv.ecommerce.domain.user.model.UserId;

public interface UserEvent extends DomainEvent {
    UserId userId();
}
