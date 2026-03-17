package at.fhv.ecommerce.infrastructure.persistence.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import at.fhv.ecommerce.infrastructure.persistence.user.entity.UserEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("SELECT u from User u LEFT JOIN FETCH u.cart WHERE u.id = :id")
    Optional<UserEntity> findByIdWithCartItems(UUID id);

    @Query("SELECT u from User u LEFT JOIN FETCH u.cart")
    List<UserEntity> findAllWithCartItems(Pageable pageable);
}
