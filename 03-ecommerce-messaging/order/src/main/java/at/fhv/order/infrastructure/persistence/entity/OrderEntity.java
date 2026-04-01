package at.fhv.order.infrastructure.persistence.entity;

import at.fhv.order.domain.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "Order")
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Order.Status status;

    @Column(nullable = false)
    private Integer reducedItemsCount;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItemEmbeddable> items;
}