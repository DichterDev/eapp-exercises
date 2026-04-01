package at.fhv.product.application.handler;

import at.fhv.product.application.query.GetProductById;
import at.fhv.product.application.query.GetProductDetailById;
import at.fhv.product.domain.port.ProductReadRepository;
import at.fhv.product.projection.Product;
import at.fhv.product.projection.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductQueryHandlerService implements ProductQueryHandler {
    private final ProductReadRepository repository;

    @Override
    public Product get(GetProductById query) {
        return repository.get(query.productId()).orElseThrow();
    }

    @Override
    public ProductDetail getDetail(GetProductDetailById query) {
        return repository.getDetail(query.productId()).orElseThrow();
    }
}
