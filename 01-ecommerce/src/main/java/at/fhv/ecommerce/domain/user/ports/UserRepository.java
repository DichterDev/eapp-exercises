package at.fhv.ecommerce.domain.user.ports;

import at.fhv.ecommerce.domain.common.BaseRepository;
import at.fhv.ecommerce.domain.user.model.User;
import at.fhv.ecommerce.domain.user.model.UserId;

public interface UserRepository extends BaseRepository<User, UserId> {
}
