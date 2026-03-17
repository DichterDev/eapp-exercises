package at.fhv.ecommerce.infrastructure.persistence.user.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.domain.user.model.CartItem;
import at.fhv.ecommerce.domain.user.model.User;
import at.fhv.ecommerce.domain.user.model.UserId;
import at.fhv.ecommerce.domain.user.ports.UserRepository;
import at.fhv.ecommerce.infrastructure.persistence.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {
    private final JpaUserRepository jpa;
    private final UserMapper mapper;

    @Override
    public void save(User model) {
        var entity = mapper.toEntity(model);
        jpa.save(entity);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return jpa.findById(id.value()).map(mapper::toModel);
    }

    @Override
    public Optional<User> findByIdWithCartItems(UserId id) {
        return jpa.findByIdWithCartItems(id.value()).map(mapper::toModel);
    }

    @Override
    public List<CartItem> findCartItemsById(UserId id) {
        return jpa.findByIdWithCartItems(id.value()).map(mapper::toModel).orElseThrow().getCart();
    }

    @Override
    public List<User> all(Integer page, Integer size) {
        return jpa.findAll(PageRequest.of(page, size)).map(mapper::toModel).toList();
    }

    @Override
    public List<User> allWithCartItems(Integer page, Integer size) {
        return jpa.findAllWithCartItems(PageRequest.of(page, size))
            .stream()
            .map(mapper::toModel)
            .toList();
    }

}
