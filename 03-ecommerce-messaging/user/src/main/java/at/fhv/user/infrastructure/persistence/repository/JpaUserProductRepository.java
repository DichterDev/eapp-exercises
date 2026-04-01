package at.fhv.user.infrastructure.persistence.repository;

import at.fhv.user.infrastructure.persistence.entity.UserProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserProductRepository extends JpaRepository<UserProductEntity, UUID> {
}