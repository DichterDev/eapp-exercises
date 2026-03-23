package at.fhv.ecommerce.user.presentation;

import at.fhv.ecommerce.user.read.application.handler.UserQueryHandler;
import at.fhv.ecommerce.user.read.application.query.GetUserById;
import at.fhv.ecommerce.user.read.application.query.GetUserDetailById;
import at.fhv.ecommerce.user.read.application.query.GetUserOrdersById;
import at.fhv.ecommerce.user.read.projection.User;
import at.fhv.ecommerce.user.read.projection.UserDetail;
import at.fhv.ecommerce.user.read.projection.UserOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRead {
    private final UserQueryHandler handler;

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable UUID id) {
        var res = handler.get(new GetUserById(id));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<UserDetail> getDetail(@PathVariable UUID id) {
        var res = handler.getDetail(new GetUserDetailById(id));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<UserOrder>> getOrders(@PathVariable UUID id) {
        var res = handler.getOrders(new GetUserOrdersById(id));
        return ResponseEntity.ok(res);
    }
}
