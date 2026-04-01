package at.fhv.user.infrastructure.persistence.repository;

import at.fhv.user.domain.port.UserOrderReadRepository;
import at.fhv.user.infrastructure.persistence.mapper.UserOrderMapper;
import at.fhv.user.projection.UserOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SpringUserOrderReadRepository implements UserOrderReadRepository {

    private final JpaUserOrderRepository jpa;
    private final UserOrderMapper mapper;

    @Override
    public List<UserOrder> findByUserId(UUID userId) {
        return jpa.findByUserId(userId)
                .stream()
                .map(mapper::toProjection)
                .toList();
    }

    @Override
    public Optional<UserOrder> findByOrderId(UUID orderId) {
        return jpa.findByOrderId(orderId)
                .map(mapper::toProjection);
    }
}