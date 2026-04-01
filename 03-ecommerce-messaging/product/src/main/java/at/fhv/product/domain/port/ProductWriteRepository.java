package at.fhv.product.domain.port;

import at.fhv.product.domain.model.Product;
import at.fhv.product.domain.model.ProductId;

import java.util.Optional;

public interface ProductWriteRepository {

    void save(Product product);

    Optional<Product> findById(ProductId id);
}
