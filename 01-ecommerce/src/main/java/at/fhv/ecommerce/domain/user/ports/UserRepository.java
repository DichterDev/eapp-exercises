package at.fhv.ecommerce.domain.user.ports;

import java.util.List;
import java.util.Optional;
import at.fhv.ecommerce.domain.common.BaseRepository;
import at.fhv.ecommerce.domain.user.model.CartItem;
import at.fhv.ecommerce.domain.user.model.User;
import at.fhv.ecommerce.domain.user.model.UserId;

public interface UserRepository extends BaseRepository<User, UserId> {
    Optional<User> findByIdWithCartItems(UserId id);

    List<CartItem> findCartItemsById(UserId id);

    List<User> all(Integer page, Integer size);

    List<User> allWithCartItems(Integer page, Integer size);
}
