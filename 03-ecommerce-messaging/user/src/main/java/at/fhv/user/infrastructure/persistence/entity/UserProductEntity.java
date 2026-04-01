package at.fhv.user.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProductEntity {

    @Id
    private UUID productId;

    private BigDecimal price;

    private String currency;
}