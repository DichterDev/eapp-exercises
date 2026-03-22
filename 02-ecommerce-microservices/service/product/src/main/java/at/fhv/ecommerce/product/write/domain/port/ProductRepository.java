package at.fhv.ecommerce.product.write.domain.port;

import java.util.Optional;
import at.fhv.ecommerce.product.write.domain.model.Product;
import at.fhv.ecommerce.product.write.domain.model.ProductId;

public interface ProductRepository {
    void save(Product product);

    Optional<Product> findById(ProductId id);
}
