package at.fhv.ecommerce.infrastructure.persistence.order.entity;

import java.util.UUID;
import at.fhv.ecommerce.infrastructure.persistence.common.entity.MoneyEmbed;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEmbeddable {
    @Column(nullable = false)
    private UUID productId;

    @Column
    private Integer amount;

    @Embedded
    private MoneyEmbed price;
}
