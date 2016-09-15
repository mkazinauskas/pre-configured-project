package authorization.domain.user

import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@CompileStatic
interface Users extends JpaRepository<User, Long> {
    @Query('SELECT u FROM User u where u.email = :email')
    Optional<User> findByEmail(@Param('email') String email)
}
