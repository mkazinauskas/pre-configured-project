package preconfigured.authorization.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import preconfigured.authorization.api.user.domain.User;
import preconfigured.authorization.api.user.domain.UserRepository;

import java.util.Optional;

@Component
public class UserAuthorizationService implements UserDetailsService {

    private UserRepository userRepository;

    private UserToUserDetailsMapper mapper;

    @Autowired
    public UserAuthorizationService(UserRepository userRepository, UserToUserDetailsMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUserByEmail = userRepository.findByEmail(username);

        return foundUserByEmail
                .map(mapper::map)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
