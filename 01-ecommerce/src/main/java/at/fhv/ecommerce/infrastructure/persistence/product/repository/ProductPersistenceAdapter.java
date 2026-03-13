package at.fhv.ecommerce.infrastructure.persistence.product.repository;

import java.util.Optional;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.domain.product.model.Product;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.product.ports.ProductRepository;
import at.fhv.ecommerce.infrastructure.persistence.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepository {
    private final JpaProductRepository jpa;
    private final ProductMapper mapper;

    @Override
    public void save(Product model) {
        var entity = mapper.toEntity(model);

        jpa.save(entity);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return jpa.findById(id.value()).map(mapper::toModel);
    }
}
