package at.fhv.user.application.handler;

import at.fhv.user.application.query.*;
import at.fhv.user.domain.port.UserReadRepository;
import at.fhv.user.projection.User;
import at.fhv.user.projection.UserDetail;
import at.fhv.user.projection.UserOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserQueryHandlerService implements UserQueryHandler {
    private final UserReadRepository repository;

    @Override
    public User get(GetUserById query) {
        return repository.get(query.userId()).orElseThrow();
    }

    @Override
    public UserDetail getDetail(GetUserDetailById query) {
        return repository.getDetail(query.userId()).orElseThrow();
    }

    @Override
    public List<UserOrder> getOrders(GetUserOrdersById query) {
        return repository.getOrders(query.userId());
    }

}