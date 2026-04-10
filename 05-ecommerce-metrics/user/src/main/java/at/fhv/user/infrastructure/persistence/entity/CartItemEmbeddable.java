package at.fhv.user.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class CartItemEmbeddable {
    @Column(nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private Integer amount;
}

