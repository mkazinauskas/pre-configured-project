package authorization.domain.commands

import authorization.domain.UserAuthorityType

import static UserAuthorityType.USER

class CreateUserCommand {
    String email
    String password
    Set<UserAuthorityType> authorities = [USER]
}
