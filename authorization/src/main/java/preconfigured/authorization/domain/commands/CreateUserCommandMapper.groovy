package preconfigured.authorization.domain.commands

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import preconfigured.authorization.domain.User

@Component
class CreateUserCommandMapper {

    @Autowired
    private PasswordEncoder passwordEncoder

    User mapFrom(CreateUserCommand command){
        new User(
                email: command.email,
                encodedPassword: passwordEncoder.encode(command.password)

        )
    }
}
