package authorization.domain.user.commands

import authorization.domain.user.UserAuthorityType

import static UserAuthorityType.USER

class CreateUser {
    String email
    String password
    Set<UserAuthorityType> authorities = [USER]
}
