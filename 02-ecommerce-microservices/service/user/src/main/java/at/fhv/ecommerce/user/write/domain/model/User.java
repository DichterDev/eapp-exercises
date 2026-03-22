package at.fhv.ecommerce.user.write.domain.model;

import java.util.ArrayList;
import java.util.List;
import at.fhv.ecommerce.common.domain.BaseDomainRoot;
import at.fhv.ecommerce.user.write.domain.event.UserCartAddedItem;
import at.fhv.ecommerce.user.write.domain.event.UserCartCheckedOut;
import at.fhv.ecommerce.user.write.domain.event.UserCartCompletedCheckout;
import at.fhv.ecommerce.user.write.domain.event.UserChangedName;
import at.fhv.ecommerce.user.write.domain.event.UserRegistered;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends BaseDomainRoot {
    private final UserId id;
    private String name;

    @Builder.Default
    List<CartItem> cart = new ArrayList<>();

    public static User register(UserId id, String name) {
        var user = User.builder().id(id).name(name).build();

        user.registerEvent(new UserRegistered(user));

        return user;
    }

    public void rename(String newName) {
        String oldName = String.valueOf(this.name);

        this.name = newName;

        this.registerEvent(new UserChangedName(this, oldName));
    }

    public void addCartItem(ProductId productId, Integer amount) {
        this.cart.add(new CartItem(productId, amount));

        this.registerEvent(new UserCartAddedItem(this, productId));
    }

    public void cartCheckout() {
        this.registerEvent(new UserCartCheckedOut(this));
    }

    public void cartCompleteCheckout() {
        this.cart.clear();

        this.registerEvent(new UserCartCompletedCheckout(this));
    }
}
