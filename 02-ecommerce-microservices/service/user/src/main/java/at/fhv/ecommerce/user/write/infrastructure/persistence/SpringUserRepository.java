package at.fhv.ecommerce.user.write.infrastructure.persistence;

import java.util.Optional;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.user.write.domain.model.User;
import at.fhv.ecommerce.user.write.domain.model.UserId;
import at.fhv.ecommerce.user.write.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringUserRepository implements UserRepository {
    private final JpaUserRepository jpa;
    private final UserMapper mapper;

    @Override
    public void save(User user) {
        jpa.save(mapper.toEntity(user));
    }

    @Override
    public Optional<User> findById(UserId id) {
        return jpa.findById(id.value()).map(mapper::toModel);
    }

}
