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
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCommandHandlerService implements ProductCommandHandler {
    private final ProductWriteRepository repository;
    private final ProductEventPublisher publisher;
    private final MeterRegistry meter;

    private final Map<String, Double> priceMap = new java.util.concurrent.ConcurrentHashMap<>();
    private final Map<String, Double> stockMap = new java.util.concurrent.ConcurrentHashMap<>();

    private void syncMetrics(Product product) {
        String productId = product.getId().toString();

        priceMap.put(productId, product.getPrice().value().doubleValue());
        stockMap.put(productId, product.getStock().doubleValue());

        Gauge.builder("product.price", priceMap, map -> map.getOrDefault(productId, 0.0))
            .tags("product_id", productId, "product_name", product.getName())
            .register(meter);

        Gauge.builder("product.stock", stockMap, map -> map.getOrDefault(productId, 0.0))
            .tags("product_id", productId, "product_name", product.getName())
            .register(meter);
    }

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

        meter.counter("product.create").increment();

        syncMetrics(product);

        return new CommandResponse(cmd.productId().toString());
    }

    @Override
    public void updatePrice(ChangeProductPrice cmd) {
        var product = get(cmd.productId());

        product.updatePrice(new Money(BigDecimal.valueOf(cmd.price())));

        repository.save(product);

        meter.gauge(
            "product.price",
            List.of(
                Tag.of("product_id", product.getId().toString()),
                Tag.of("product_name", product.getName())
            ),
            product,
            p -> p.getPrice().value().doubleValue()
        );

        publisher.publishAll(product.pullEvents());
    }

    @Override
    public void reduceStock(ReduceProductStock cmd) {
        var product = get(cmd.productId());

        product.reduceStock(cmd.amount(), cmd.orderId());

        repository.save(product);

        syncMetrics(product);

        publisher.publishAll(product.pullEvents());
    }

    @Override
    public void increaseStock(IncreaseProductStock cmd) {
        var product = get(cmd.productId());

        product.increaseStock(cmd.amount());

        repository.save(product);

        syncMetrics(product);

        publisher.publishAll(product.pullEvents());
    }
}
