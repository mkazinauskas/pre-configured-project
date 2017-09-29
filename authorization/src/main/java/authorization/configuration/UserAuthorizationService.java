package authorization.configuration;

import authorization.domain.user.User;
import authorization.domain.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserAuthorizationService implements UserDetailsService {

    private final Users users;

    @Autowired
    public UserAuthorizationService(Users users) {
        this.users = users;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> foundUserByEmail = users.findByEmail(username);

        return foundUserByEmail
                .map(UserToUserDetailsMapper::map)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
