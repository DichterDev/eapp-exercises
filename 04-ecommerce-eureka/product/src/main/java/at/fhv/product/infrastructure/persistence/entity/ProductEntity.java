package at.fhv.product.infrastructure.persistence.entity;

import java.util.UUID;

import at.fhv.common.infrastructure.persistence.MoneyEmbed;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    @Embedded
    private MoneyEmbed price;

    @Column(nullable = false)
    private Integer stock;
}