package at.fhv.user.infrastructure.messaging;

import at.fhv.common.domain.event.product.ProductChangedPrice;
import at.fhv.common.domain.event.product.ProductCreated;
import at.fhv.user.infrastructure.persistence.entity.UserProductEntity;
import at.fhv.user.infrastructure.persistence.repository.JpaUserProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class UserProductEventConsumer {

    private final JpaUserProductRepository repository;

    @Bean
    public Consumer<ProductCreated> productCreatedIn() {
        return event -> {
            repository.save(
                    UserProductEntity.builder()
                            .productId(event.productId())
                            .price(event.price().value())
                            .currency(event.price().currency().getCurrencyCode())
                            .build()
            );
        };
    }

    @Bean
    public Consumer<ProductChangedPrice> productChangedPriceIn() {
        return event -> {
            repository.save(
                    UserProductEntity.builder()
                            .productId(event.productId())
                            .price(event.newPrice().value())
                            .currency(event.newPrice().currency().getCurrencyCode())
                            .build()
            );
        };
    }
}