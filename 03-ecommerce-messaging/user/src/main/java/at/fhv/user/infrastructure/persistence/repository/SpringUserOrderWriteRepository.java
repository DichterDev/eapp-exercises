package at.fhv.user.infrastructure.persistence.repository;

import at.fhv.user.domain.port.UserOrderWriteRepository;
import at.fhv.user.infrastructure.persistence.entity.UserOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SpringUserOrderWriteRepository implements UserOrderWriteRepository {

    private final JpaUserOrderRepository jpa;

    @Override
    public void save(UserOrderEntity order) {
        jpa.save(order);
    }

    @Override
    public void markCompleted(UUID orderId) {
        jpa.findById(orderId).ifPresent(order -> {
            order.setStatus("COMPLETED");
            jpa.save(order);
        });
    }

    @Override
    public void markFailed(UUID orderId) {
        jpa.findById(orderId).ifPresent(order -> {
            order.setStatus("FAILED");
            jpa.save(order);
        });
    }
}