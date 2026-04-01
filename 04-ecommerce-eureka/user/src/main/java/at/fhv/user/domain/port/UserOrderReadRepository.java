package at.fhv.user.domain.port;

import at.fhv.user.projection.UserOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserOrderReadRepository {
    List<UserOrder> findByUserId(UUID userId);

    Optional<UserOrder> findByOrderId(UUID orderId);
}