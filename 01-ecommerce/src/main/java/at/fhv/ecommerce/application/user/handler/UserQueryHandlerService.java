package at.fhv.ecommerce.application.user.handler;

import java.util.UUID;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.application.user.query.GetUserByIdQuery;
import at.fhv.ecommerce.application.user.view.UserView;
import at.fhv.ecommerce.domain.user.model.UserId;
import at.fhv.ecommerce.domain.user.ports.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryHandlerService implements UserQueryHandler {
    private final UserRepository repository;

    @Override
    public UserView handleGet(GetUserByIdQuery query) {
        var user = repository.findById(new UserId(query.id())).orElseThrow();

        return new UserView(
            user.getId().value(),
            user.getName()
        );
    }
}
