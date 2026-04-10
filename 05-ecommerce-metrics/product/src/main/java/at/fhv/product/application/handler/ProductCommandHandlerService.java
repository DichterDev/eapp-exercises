package at.fhv.product.application.handler;

import at.fhv.common.application.command.CommandResponse;
import at.fhv.common.domain.model.Money;
import at.fhv.product.application.command.ChangeProductPrice;
import at.fhv.product.application.command.CreateProduct;
import at.fhv.product.application.command.IncreaseProductStock;
import at.fhv.product.application.command.ReduceProductStock;
import at.fhv.product.domain.model.Product;
import at.fhv.product.domain.model.ProductId;
import at.fhv.product.domain.port.ProductEventPublisher;
import at.fhv.product.domain.port.ProductWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCommandHandlerService implements ProductCommandHandler {
    private final ProductWriteRepository repository;
    private final ProductEventPublisher publisher;

    private Product get(UUID id) {

        return repository.findById(new ProductId(id)).orElseThrow();
    }
    @Override
    public CommandResponse create(CreateProduct cmd) {
        var product = Product.create(
                new ProductId(cmd.productId()),
                cmd.name(),
                cmd.description(),
                new Money(BigDecimal.valueOf(cmd.price())),
                cmd.stock()
        );

        repository.save(product);

        publisher.publishAll(product.pullEvents());

        return new CommandResponse(cmd.productId().toString());
    }

    @Override
    public void updatePrice(ChangeProductPrice cmd) {
        var product = get(cmd.productId());

        product.updatePrice(new Money(BigDecimal.valueOf(cmd.price())));

        repository.save(product);

        publisher.publishAll(product.pullEvents());
    }

    @Override
    public void reduceStock(ReduceProductStock cmd) {
        var product = get(cmd.productId());

        product.reduceStock(cmd.amount(), cmd.orderId());

        repository.save(product);

        publisher.publishAll(product.pullEvents());
    }

    @Override
    public void increaseStock(IncreaseProductStock cmd) {
        var product = get(cmd.productId());

        product.increaseStock(cmd.amount());

        repository.save(product);

        publisher.publishAll(product.pullEvents());
    }
}





