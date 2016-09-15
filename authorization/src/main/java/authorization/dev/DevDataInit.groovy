package authorization.dev

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import authorization.domain.user.User
import authorization.domain.user.commands.CreateUser
import authorization.domain.user.commands.CreateUserHandler

@Component
public class DevDataInit {

    @Autowired
    private CreateUserHandler createUserCommandHandler;

    private final List<User> USERS = [
            new CreateUser(email: 'testUser1@mail.com', password: 'password'),
            new CreateUser(email: 'testUser2@mail.com', password: 'password')
    ]

    @Bean
    public CommandLineRunner init() {
        return new CommandLineRunner() {
            @Override
            void run(String... args) throws Exception {
                USERS.each { createUserCommandHandler.createUser(it) }
            }
        }
    }
}
