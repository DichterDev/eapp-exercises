package at.fhv.product.infrastructure.persistence.repository;

import at.fhv.product.domain.model.Product;
import at.fhv.product.domain.model.ProductId;
import at.fhv.product.domain.port.ProductWriteRepository;
import at.fhv.product.infrastructure.persistence.mapper.ProductWriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SpringProductWriteRepository implements ProductWriteRepository {
    private final JpaProductRepository jpa;
    private final ProductWriteMapper mapper;

    @Override
    public void save(Product product) {
        jpa.save(mapper.toEntity(product));
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return jpa.findById(id.value()).map(mapper::toDomain);
    }
}

