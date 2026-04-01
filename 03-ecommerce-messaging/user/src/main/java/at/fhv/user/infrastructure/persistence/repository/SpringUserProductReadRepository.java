package at.fhv.user.infrastructure.persistence.repository;

import at.fhv.common.domain.model.Money;
import at.fhv.user.domain.port.UserProductReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SpringUserProductReadRepository implements UserProductReadRepository {

    private final JpaUserProductRepository jpa;

    @Override
    public Money getPrice(UUID productId) {
        var entity = jpa.findById(productId)
                .orElseThrow();

        return new Money(
                entity.getPrice(),
                Currency.getInstance(entity.getCurrency())
        );
    }
}