package at.fhv.user.infrastructure.persistence.repository;

import at.fhv.user.domain.model.User;
import at.fhv.user.domain.model.UserId;
import at.fhv.user.domain.port.UserWriteRepository;
import at.fhv.user.infrastructure.persistence.mapper.UserWriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SpringUserWriteRepository implements UserWriteRepository {
    private final JpaUserRepository jpa;
    private final UserWriteMapper mapper;

    @Override
    public void save(User user) {
        jpa.save(mapper.toEntity(user));
    }

    @Override
    public Optional<User> findById(UserId id) {
        return jpa.findById(id.value()).map(mapper::toDomain);
    }
}
