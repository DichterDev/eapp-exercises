package at.fhv.ecommerce.user.write.infrastructure.persistence;

import java.util.UUID;
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
public class CartItemEmbeddable {
    @Column(nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private Integer amount;
}
