package at.fhv.user.presentation;

import at.fhv.user.application.handler.UserQueryHandler;
import at.fhv.user.application.query.GetUserById;
import at.fhv.user.application.query.GetUserDetailById;
import at.fhv.user.projection.User;
import at.fhv.user.projection.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users/q")
@RequiredArgsConstructor
public class UserReadController {

    private final UserQueryHandler query;

    @GetMapping("/{id}")
    public User get(@PathVariable UUID id) {
        return query.get(new GetUserById(id));
    }

    @GetMapping("/detail/{id}")
    public UserDetail getDetail(@PathVariable UUID id) {
        return query.getDetail(new GetUserDetailById(id));
    }
}
