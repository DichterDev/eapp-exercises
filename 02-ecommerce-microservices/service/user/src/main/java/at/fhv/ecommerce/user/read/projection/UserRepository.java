package at.fhv.ecommerce.user.read.projection;

import java.util.UUID;

public interface UserRepository {
    User findById(UUID userId);

    UserDetail findDetailById(UUID userId);
}
