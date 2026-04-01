package at.fhv.user.domain.port;

import at.fhv.user.domain.model.User;
import at.fhv.user.domain.model.UserId;

import java.util.Optional;

public interface UserWriteRepository {
    void save(User user);

    Optional<User> findById(UserId id);
}
