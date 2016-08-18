package preconfigured.authorization.dev

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import preconfigured.authorization.api.user.domain.User
import preconfigured.authorization.api.user.domain.UserRepository

public class DevDataInit {

    @Autowired
    private UserRepository userRepository;

    //@Bean
    public CommandLineRunner init() {
        return new CommandLineRunner() {
            @Override
            void run(String... args) throws Exception {
                userRepository.save(
                        new User(

                        )
                );
            }
        }
    }
}
