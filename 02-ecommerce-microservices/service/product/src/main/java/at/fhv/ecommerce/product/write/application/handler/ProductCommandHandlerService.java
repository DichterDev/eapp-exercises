package at.fhv.ecommerce.product.write.application.handler;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.common.domain.Money;
import at.fhv.ecommerce.product.write.application.command.ChangeProductPrice;
import at.fhv.ecommerce.product.write.application.command.CreateProduct;
import at.fhv.ecommerce.product.write.application.command.IncreaseProductStock;
import at.fhv.ecommerce.product.write.application.command.ReduceProductStock;
import at.fhv.ecommerce.product.write.domain.model.Product;
import at.fhv.ecommerce.product.write.domain.model.ProductId;
import at.fhv.ecommerce.product.write.domain.port.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCommandHandlerService implements ProductCommandHandler {
    private final ProductRepository repository;

    private Product get(UUID id) {
        return repository.findById(new ProductId(id)).orElseThrow();
    }

    @Override
    @Transactional
    public CommandResponse create(CreateProduct cmd) {
        var product = Product.create(
            new ProductId(cmd.id()),
            cmd.name(),
            cmd.description(),
            new Money(BigDecimal.valueOf(cmd.price())),
            cmd.stock()
        );

        repository.save(product);

        return new CommandResponse(cmd.id().toString());
    }

    @Override
    @Transactional
    public void updatePrice(ChangeProductPrice cmd) {
        var product = get(cmd.productId());

        product.updatePrice(new Money(BigDecimal.valueOf(cmd.newPrice())));

        repository.save(product);
    }

    @Override
    @Transactional
    public void reduceStock(ReduceProductStock cmd) {
        var product = get(cmd.productId());

        product.reduceStock(cmd.amount());

        repository.save(product);
    }

    @Override
    @Transactional
    public void increaseStock(IncreaseProductStock cmd) {
        var product = get(cmd.productId());

        product.increaseStock(cmd.amount());

        repository.save(product);
    }
}
