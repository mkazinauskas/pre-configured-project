package authorization.domain.user.commands

import authorization.domain.user.User
import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
@CompileStatic
@PackageScope
class CreateUserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder

    User mapFrom(CreateUser command) {
        new User().with {
            email = command.email
            encodedPassword = passwordEncoder.encode(command.password)
            authorities.addAll command.authorities
            it
        }
    }
}
