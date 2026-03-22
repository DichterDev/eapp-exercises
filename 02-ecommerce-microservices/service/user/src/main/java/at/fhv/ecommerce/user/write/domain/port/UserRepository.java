package at.fhv.ecommerce.user.write.domain.port;

import java.util.Optional;
import at.fhv.ecommerce.user.write.domain.model.User;
import at.fhv.ecommerce.user.write.domain.model.UserId;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(UserId id);
}
