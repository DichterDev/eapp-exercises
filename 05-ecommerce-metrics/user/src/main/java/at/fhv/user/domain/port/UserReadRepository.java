package at.fhv.user.domain.port;

import at.fhv.user.projection.User;
import at.fhv.user.projection.UserDetail;
import at.fhv.user.projection.UserOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserReadRepository {
    Optional<User> get(UUID id);

    Optional<UserDetail> getDetail(UUID id);
}
