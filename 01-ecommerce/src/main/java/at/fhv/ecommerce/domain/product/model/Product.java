package at.fhv.ecommerce.domain.product.model;

import at.fhv.ecommerce.domain.common.DomainRoot;
import at.fhv.ecommerce.domain.common.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product extends DomainRoot {
    @Builder.Default
    private final ProductId id = ProductId.generate();
    private String name;
    private String description;

    private Money price;
    private Integer stock;

    public static Product create(String name, String description, Money price, Integer stock) {
        var product = Product.builder()
            .name(name)
            .description(description)
            .price(price)
            .stock(stock)
            .build();

        return product;
    }

    public void updatePrice(Money newPrice) {
        this.price = newPrice;
    }

    public void increaseStock(Integer amount) {
        this.stock += amount;
    }

    public void reduceStock(Integer amount) {
        if (this.stock.compareTo(amount) <= 0) {
            this.stock -= amount;
        }
    }
}
