package at.fhv.ecommerce.order.write.domain.model;

import at.fhv.ecommerce.common.domain.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderItem {
    private final ProductId productId;
    private final Integer amount;

    private Money price;

    public static OrderItem add(ProductId productId, Integer amount) {
        var item = OrderItem.builder()
            .productId(productId)
            .amount(amount)
            .build();

        return item;
    }
}
