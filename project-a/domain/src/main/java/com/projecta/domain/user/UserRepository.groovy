package com.projecta.domain.user

import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository

@CompileStatic
interface UserRepository extends JpaRepository<User, Long> {
}
