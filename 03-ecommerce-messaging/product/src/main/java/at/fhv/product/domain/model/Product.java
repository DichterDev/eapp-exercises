package at.fhv.product.domain.model;

import at.fhv.common.domain.event.product.*;
import at.fhv.common.domain.model.BaseDomainRoot;
import at.fhv.common.domain.model.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product extends BaseDomainRoot {
    @Builder.Default
    private final ProductId id = ProductId.generate();
    private String name;
    private String description;
    private Money price;
    private Integer stock;

    public static Product create(ProductId id, String name, String description, Money price,
                                 Integer stock) {
        var product = Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .build();

        product.registerEvent(new ProductCreated(product.id.value(), product.getName(), product.getDescription(), product.getPrice(), product.getStock()));

        return product;
    }

    public void updatePrice(Money newPrice) {
        this.price = newPrice;

        this.registerEvent(new ProductChangedPrice(this.id.value(), newPrice));

    }

    public void increaseStock(Integer amount) {
        this.stock += amount;

        this.registerEvent(new ProductIncreasedStock(this.id.value(), amount));
    }

    public void reduceStock(Integer amount, UUID orderId) {
        if (this.stock.compareTo(amount) < 0) {
            this.registerEvent(new ProductFailedToReduceStock(
                    this.id.value(),
                    orderId,
                    amount,
                    "Not enough stock"
            ));
            return;
        }

        this.stock -= amount;

        this.registerEvent(new ProductReducedStock(
                this.id.value(),
                orderId,
                amount
        ));
    }
}