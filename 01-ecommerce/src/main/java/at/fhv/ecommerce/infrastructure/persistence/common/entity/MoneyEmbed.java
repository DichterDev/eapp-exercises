package at.fhv.ecommerce.infrastructure.persistence.common.entity;

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
public class MoneyEmbed {
    @Column(name = "price_amount")
    private BigDecimal amount;

    @Column(name = "price_currency")
    private String currency;
}
