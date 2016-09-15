package authorization.domain.user.commands;

import authorization.domain.user.User;
import authorization.domain.user.Users;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateUserHandler {

    private Users users;

    private CreateUserMapper mapper;

    public CreateUserHandler(Users users, CreateUserMapper mapper) {
        this.users = users;
        this.mapper = mapper;
    }

    @Transactional
    public CreateUserResponse createUser(CreateUser command) {
        User user = mapper.mapFrom(command);
        User savedUser = users.save(user);
        return new CreateUserResponse(savedUser.getId());
    }
}
