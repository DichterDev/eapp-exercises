package at.fhv.ecommerce.user.write.domain.event;

import at.fhv.ecommerce.common.domain.DomainEvent;
import at.fhv.ecommerce.user.write.domain.model.UserId;

public interface UserEvent extends DomainEvent {
    UserId userId();
}
