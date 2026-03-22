package at.fhv.ecommerce.order.write.domain.model;

import java.util.ArrayList;
import java.util.List;
import at.fhv.ecommerce.common.domain.BaseDomainRoot;
import at.fhv.ecommerce.order.write.domain.event.OrderCancelled;
import at.fhv.ecommerce.order.write.domain.event.OrderCompleted;
import at.fhv.ecommerce.order.write.domain.event.OrderFailed;
import at.fhv.ecommerce.order.write.domain.event.OrderPlaced;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Order extends BaseDomainRoot {
    private final OrderId id;
    private final UserId userId;

    @Builder.Default
    private Status status = Status.PENDING;

    @Builder.Default
    List<OrderItem> items = new ArrayList<>();

    public enum Status {
        PENDING, COMPLETED, FAILED, CANCELLED
    }

    public static Order place(OrderId id, UserId userId) {
        var order = Order.builder()
            .id(id)
            .userId(userId)
            .build();

        order.registerEvent(new OrderPlaced(order));

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
        this.registerEvent(new OrderCompleted(this));
    }

    public void cancel() {
        if (this.status != Status.PENDING) {
            return;
        }

        this.status = Status.CANCELLED;
        this.registerEvent(new OrderCancelled(this));
    }

    public void fail() {
        if (this.status != Status.PENDING) {
            return;
        }

        this.status = Status.FAILED;
        this.registerEvent(new OrderFailed(this));

    }
}
