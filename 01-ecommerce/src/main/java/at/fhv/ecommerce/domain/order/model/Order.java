package at.fhv.ecommerce.domain.order.model;

import java.util.ArrayList;
import java.util.List;
import at.fhv.ecommerce.domain.common.DomainRoot;
import at.fhv.ecommerce.domain.order.events.OrderCancelledEvent;
import at.fhv.ecommerce.domain.order.events.OrderCompletedEvent;
import at.fhv.ecommerce.domain.order.events.OrderFailedEvent;
import at.fhv.ecommerce.domain.order.events.OrderPlacedEvent;
import at.fhv.ecommerce.domain.product.model.ProductId;
import at.fhv.ecommerce.domain.user.model.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Order extends DomainRoot {
    @Builder.Default
    private final OrderId id = OrderId.generate();
    private final UserId userId;

    @Builder.Default
    private Status status = Status.PENDING;

    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    public enum Status {
        PENDING, COMPLETED, FAILED, CANCELLED,
    }

    public static Order place(UserId userId) {
        var order = Order.builder()
            .userId(userId)
            .build();

        order.registerEvent(new OrderPlacedEvent(order));

        return order;
    }

    public void addItem(ProductId productId, Integer amount) {
        var item = OrderItem.add(productId, amount);

        this.items.add(item);
    }

    public void complete() {
        if (this.status != Status.PENDING) {
            return;
        }

        this.status = Status.COMPLETED;
        this.registerEvent(new OrderCompletedEvent(this));
    }

    public void cancel() {
        if (this.status != Status.PENDING) {
            return;
        }

        this.status = Status.CANCELLED;
        this.registerEvent(new OrderCancelledEvent(this));
    }

    public void fail() {
        if (this.status != Status.PENDING) {
            return;
        }

        this.status = Status.FAILED;
        this.registerEvent(new OrderFailedEvent(this));

    }
}
