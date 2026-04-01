package at.fhv.order.infrastructure.persistence.entity;

import at.fhv.common.infrastructure.persistence.MoneyEmbed;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

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