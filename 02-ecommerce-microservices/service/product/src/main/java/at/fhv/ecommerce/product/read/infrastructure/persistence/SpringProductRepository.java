package at.fhv.ecommerce.product.read.infrastructure.persistence;

import java.util.UUID;
import org.springframework.stereotype.Component;
import at.fhv.ecommerce.product.read.projection.Product;
import at.fhv.ecommerce.product.read.projection.ProductDetail;
import at.fhv.ecommerce.product.read.projection.ProductRepository;
import at.fhv.ecommerce.product.write.infrastructure.persistence.JpaProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringProductRepository implements ProductRepository {
    private final JpaProductRepository jpa;

    @Override
    public Product findById(UUID id) {
        var product = jpa.findById(id).orElseThrow();

        return new Product(
            product.getId().toString(),
            product.getName(),
            product.getPrice().getAmount().floatValue(),
            product.getPrice().getCurrency()
        );
    }

    @Override
    public ProductDetail findDetailById(UUID id) {
        var product = jpa.findById(id).orElseThrow();

        return new ProductDetail(
            product.getId().toString(),
            product.getName(),
            product.getDescription(),
            product.getPrice().getAmount().floatValue(),
            product.getPrice().getCurrency()
        );
    }

}
