package at.fhv.ecommerce.product.write.domain.model;

import at.fhv.ecommerce.common.domain.BaseDomainRoot;
import at.fhv.ecommerce.common.domain.Money;
import at.fhv.ecommerce.product.write.domain.event.ProductChangedPrice;
import at.fhv.ecommerce.product.write.domain.event.ProductCreated;
import at.fhv.ecommerce.product.write.domain.event.ProductIncreasedStock;
import at.fhv.ecommerce.product.write.domain.event.ProductReducedStock;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product extends BaseDomainRoot {
    private final ProductId id;
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

        product.registerEvent(new ProductCreated(product));

        return product;
    }

    public void updatePrice(Money newPrice) {
        Money oldPrice = this.price;
        this.price = newPrice;

        this.registerEvent(new ProductChangedPrice(this, oldPrice));
    }

    public void increaseStock(Integer amount) {
        this.stock += amount;

        this.registerEvent(new ProductIncreasedStock(this, amount));
    }

    public void reduceStock(Integer amount) {
        if (this.stock < amount)
            return;
        this.stock -= amount;

        this.registerEvent(new ProductReducedStock(this, amount));
    }
}
