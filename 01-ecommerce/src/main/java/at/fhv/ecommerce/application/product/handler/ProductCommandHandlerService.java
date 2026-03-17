package at.fhv.ecommerce.application.product.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.product.command.CreateProductCommand;
import at.fhv.ecommerce.application.product.command.IncreaseProductStockCommand;
import at.fhv.ecommerce.application.product.command.ReduceProductStockCommand;
import at.fhv.ecommerce.application.product.command.UpdateProductPriceCommand;
import at.fhv.ecommerce.domain.common.Money;
import at.fhv.ecommerce.domain.product.model.Product;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.product.ports.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCommandHandlerService implements ProductCommandHandler {

    private final ProductRepository repository;

    @Override
    @Transactional
    public CommandResponse handleCreate(CreateProductCommand cmd) {
        var product = Product.create(
            new ProductId(cmd.productId()),
            cmd.name(),
            cmd.description(),
            new Money(cmd.price()),
            cmd.stock()
        );

        repository.save(product);

        return new CommandResponse(product.getId().value().toString());
    }

    @Override
    @Transactional
    public void handleIncreaseStock(IncreaseProductStockCommand cmd) {
        var product = repository.findById(new ProductId(cmd.productId())).orElseThrow();

        product.increaseStock(cmd.amount());

        repository.save(product);
    }

    @Override
    @Transactional
    public void handleReduceStock(ReduceProductStockCommand cmd) {
        var product = repository.findById(new ProductId(cmd.productId())).orElseThrow();

        product.reduceStock(cmd.amount());

        repository.save(product);
    }

    @Override
    @Transactional
    public void handlePriceUpdate(UpdateProductPriceCommand cmd) {
        var product = repository.findById(new ProductId(cmd.productId())).orElseThrow();

        product.updatePrice(cmd.newPrice());

        repository.save(product);
    }
}
