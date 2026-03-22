package at.fhv.ecommerce.user.read.application.handler;

import java.util.List;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.user.read.application.query.GetUserById;
import at.fhv.ecommerce.user.read.application.query.GetUserDetailById;
import at.fhv.ecommerce.user.read.application.query.GetUserOrdersById;
import at.fhv.ecommerce.user.read.projection.User;
import at.fhv.ecommerce.user.read.projection.UserDetail;
import at.fhv.ecommerce.user.read.projection.UserOrder;
import at.fhv.ecommerce.user.read.projection.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryHandlerService implements UserQueryHandler {
    private final UserRepository repository;

    @Override
    public User get(GetUserById query) {
        return repository.findById(query.userId());
    }

    @Override
    public UserDetail getDetail(GetUserDetailById query) {
        return repository.findDetailById(query.userId());
    }

    @Override
    public List<UserOrder> getOrders(GetUserOrdersById query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrders'");
    }

}
