package at.fhv.user.infrastructure.persistence.repository;

import at.fhv.user.domain.port.UserReadRepository;
import at.fhv.user.infrastructure.persistence.mapper.UserReadMapper;
import at.fhv.user.projection.User;
import at.fhv.user.projection.UserDetail;
import at.fhv.user.projection.UserOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SpringUserReadRepository implements UserReadRepository {
    private final JpaUserRepository jpa;
    private final UserReadMapper mapper;

    @Override
    public Optional<User> get(UUID id) {
        return jpa.findById(id).map(mapper::toProjection);
    }

    @Override
    public Optional<UserDetail> getDetail(UUID id) {
        return jpa.findById(id).map(mapper::toDetail);
    }
}
