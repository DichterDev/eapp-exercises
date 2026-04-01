package at.fhv.user.domain.port;

import at.fhv.common.domain.model.Money;

import java.util.UUID;

public interface UserProductReadRepository {
    Money getPrice(UUID productId);
}