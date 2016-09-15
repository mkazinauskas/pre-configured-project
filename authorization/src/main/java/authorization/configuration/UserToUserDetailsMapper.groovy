package authorization.configuration

import groovy.transform.CompileStatic
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import authorization.domain.user.User

@Component
@CompileStatic
class UserToUserDetailsMapper {
    UserDetails map(User user) {
        new org.springframework.security.core.userdetails.User(
                user.email,
                user.encodedPassword,
                user.enabled,
                user.accountNotExpired,
                user.credentialsNonExpired,
                user.accountNotLocked,
                user.authorities
                        .collect { new SimpleGrantedAuthority(it.name()) }
        )
    }
}
