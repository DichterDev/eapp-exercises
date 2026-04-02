package at.fhv.order.infrastructure.persistence.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyEmbeddable {
    @Column(name = "price_per_unit")
    private BigDecimal amount;

    @Column(name = "price_currency")
    private String currency;
}
