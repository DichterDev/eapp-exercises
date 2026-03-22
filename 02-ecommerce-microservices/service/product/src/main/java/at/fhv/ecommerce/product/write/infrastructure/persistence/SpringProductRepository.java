package at.fhv.ecommerce.product.write.infrastructure.persistence;

import java.util.Optional;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.product.write.domain.model.Product;
import at.fhv.ecommerce.product.write.domain.model.ProductId;
import at.fhv.ecommerce.product.write.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringProductRepository implements ProductRepository {
    private final JpaProductRepository jpa;
    private final ProductMapper mapper;

    @Override
    public void save(Product product) {
        jpa.save(mapper.toEntity(product));
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return jpa.findById(id.value()).map(mapper::toModel);
    }

}
