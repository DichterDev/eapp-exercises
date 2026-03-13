package at.fhv.ecommerce.application.user.handler;

import at.fhv.ecommerce.application.common.command.CommandResponse;
import at.fhv.ecommerce.application.user.command.RegisterUserCommand;

public interface UserCommandHandler {
    CommandResponse handleRegister(RegisterUserCommand cmd);
}
