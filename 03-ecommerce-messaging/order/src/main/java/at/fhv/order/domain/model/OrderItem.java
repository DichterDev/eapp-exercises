package at.fhv.order.domain.model;

import at.fhv.common.domain.model.Money;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder

public class OrderItem {
    private final ProductId productId;
    private final Integer amount;

    private Money price;

    public static OrderItem add(ProductId productId, Integer amount, Money price) {
        var item = OrderItem.builder()
                .productId(productId)
                .amount(amount)
                .price(price)
                .build();

        return item;
    }

}
