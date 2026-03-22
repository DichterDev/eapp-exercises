package at.fhv.ecommerce.common.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDomainRoot {
    private final List<DomainEvent> events = new ArrayList<>();

    protected void registerEvent(DomainEvent event) {
        this.events.add(event);
    }

    public List<DomainEvent> pullEvents() {
        List<DomainEvent> cleared = List.copyOf(events);
        this.events.clear();

        return cleared;
    }
}
