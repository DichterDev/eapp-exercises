package at.fhv.product.infrastructure.persistence.repository;

import at.fhv.product.infrastructure.persistence.mapper.ProductReadMapper;
import at.fhv.product.domain.port.ProductReadRepository;
import at.fhv.product.projection.Product;
import at.fhv.product.projection.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SpringProductReadRepository implements ProductReadRepository {

    private final JpaProductRepository jpa;
    private final ProductReadMapper mapper;

    @Override
    public Optional<Product> get(UUID id) {
        return jpa.findById(id).map(mapper::toProjection);
    }

    @Override
    public Optional<ProductDetail> getDetail(UUID id) {
        return jpa.findById(id)
                .map(mapper::toDetail);
    }
}