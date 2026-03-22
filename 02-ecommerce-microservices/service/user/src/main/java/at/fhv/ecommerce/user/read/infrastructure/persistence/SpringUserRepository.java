package at.fhv.ecommerce.user.read.infrastructure.persistence;

import java.util.UUID;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.user.read.projection.CartItem;
import at.fhv.ecommerce.user.read.projection.User;
import at.fhv.ecommerce.user.read.projection.UserDetail;
import at.fhv.ecommerce.user.read.projection.UserRepository;
import at.fhv.ecommerce.user.write.infrastructure.persistence.JpaUserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringUserRepository implements UserRepository {

    private final JpaUserRepository jpa;

    @Override
    public User findById(UUID userId) {
        var user = jpa.findById(userId).orElseThrow();

        return new User(
            user.getId().toString(),
            user.getName()
        );
    }

    @Override
    public UserDetail findDetailById(UUID userId) {
        var user = jpa.findById(userId).orElseThrow();

        return new UserDetail(
            user.getId().toString(),
            user.getName(),
            user.getCart().stream().map(item -> {
                return new CartItem(item.getProductId().toString(), item.getAmount());
            }).toList()
        );
    }
}
