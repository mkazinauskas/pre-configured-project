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

    private Users users;

    private UserToUserDetailsMapper mapper;

    @Autowired
    public UserAuthorizationService(Users users, UserToUserDetailsMapper mapper) {
        this.users = users;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUserByEmail = users.findByEmail(username);

        return foundUserByEmail
                .map(mapper::map)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
