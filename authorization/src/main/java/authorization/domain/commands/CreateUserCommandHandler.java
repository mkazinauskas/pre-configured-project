package authorization.domain.commands;

import authorization.domain.User;
import authorization.domain.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateUserCommandHandler {

    private UserRepository userRepository;

    private CreateUserCommandMapper mapper;

    public CreateUserCommandHandler(UserRepository userRepository, CreateUserCommandMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional
    public CreateUserCommandResponse createUser(CreateUserCommand command) {
        User user = mapper.mapFrom(command);
        User savedUser = userRepository.save(user);
        return new CreateUserCommandResponse(savedUser.getId());
    }
}
