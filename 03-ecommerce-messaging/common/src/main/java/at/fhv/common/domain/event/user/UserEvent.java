package at.fhv.common.domain.event.user;

import at.fhv.common.domain.event.DomainEvent;

import java.util.UUID;

public interface UserEvent extends DomainEvent {
    UUID userId();
}