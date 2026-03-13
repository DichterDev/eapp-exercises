package at.fhv.ecommerce.infrastructure.persistence.product.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import at.fhv.ecommerce.infrastructure.persistence.product.entity.ProductEntity;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {

}
