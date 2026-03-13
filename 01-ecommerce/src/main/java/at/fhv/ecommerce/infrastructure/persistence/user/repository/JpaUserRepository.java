package at.fhv.ecommerce.infrastructure.persistence.user.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import at.fhv.ecommerce.infrastructure.persistence.user.entity.UserEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
}
