package at.fhv.ecommerce.user.presentation;

import at.fhv.ecommerce.common.application.CommandResponse;
import at.fhv.ecommerce.user.write.application.command.AddUserCartItem;
import at.fhv.ecommerce.user.write.application.command.ChangeUserName;
import at.fhv.ecommerce.user.write.application.command.CheckoutUserCart;
import at.fhv.ecommerce.user.write.application.command.CompleteUserCartCheckout;
import at.fhv.ecommerce.user.write.application.command.RegisterUser;
import at.fhv.ecommerce.user.write.application.handler.UserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserWrite {
    private final UserCommandHandler handler;

    @PostMapping("/register")
    public ResponseEntity<CommandResponse> register(@RequestBody RegisterUser command) {
        var res = handler.register(command);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/{id}/change-name")
    public ResponseEntity<Void> changeName(@PathVariable UUID id, @RequestBody String newName) {
        handler.rename(new ChangeUserName(id, newName));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cart/add")
    public ResponseEntity<Void> addItemToCart(
        @PathVariable UUID id,
        @RequestBody AddUserCartItem command) {
        handler.addItem(command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cart/checkout")
    public ResponseEntity<CommandResponse> checkoutCart(@PathVariable UUID id) {
        var oId = UUID.randomUUID();
        var res = handler.checkout(new CheckoutUserCart(id, oId));
        return ResponseEntity.ok(res);
    }

    @PostMapping("/{id}/cart/complete-checkout")
    public ResponseEntity<Void> completeCheckout(@PathVariable UUID id) {
        handler.completeCheckout(new CompleteUserCartCheckout(id));
        return ResponseEntity.ok().build();
    }
}
