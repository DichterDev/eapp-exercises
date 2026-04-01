package at.fhv.user.domain.port;

import at.fhv.user.infrastructure.persistence.entity.UserOrderEntity;

import java.util.UUID;

public interface UserOrderWriteRepository {
    void save(UserOrderEntity order);

    void markCompleted(UUID orderId);

    void markFailed(UUID orderId);
}
