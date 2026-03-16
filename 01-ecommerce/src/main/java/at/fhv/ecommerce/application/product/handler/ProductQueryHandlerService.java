package at.fhv.ecommerce.application.product.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import at.fhv.ecommerce.application.product.query.CheckIfProductExistsQuery;
import at.fhv.ecommerce.application.product.query.GetProductByIdQuery;
import at.fhv.ecommerce.application.product.view.ProductView;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.product.ports.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductQueryHandlerService implements ProductQueryHandler {
    private final ProductRepository repository;

    @Override
    @Transactional
    public ProductView handleGet(GetProductByIdQuery query) {
        var product = repository.findById(new ProductId(query.id())).orElseThrow();

        return new ProductView(
            product.getId().value(),
            product.getName(),
            product.getDescription(),
            product.getPrice().value(),
            product.getPrice().currency().toString(),
            product.getStock()
        );
    }

    @Override
    @Transactional
    public boolean checkIfExists(CheckIfProductExistsQuery query) {
        repository.findById(new ProductId(query.id())).orElseThrow();

        return true;
    }

}
