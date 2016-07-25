package com.projecta.domain.user

import groovy.transform.CompileStatic
import org.hibernate.validator.constraints.Email

import javax.persistence.*
import javax.validation.constraints.NotNull

@CompileStatic
@Entity
@Table(name = 'USERS')
class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id

    @Email
    @NotNull
    @Column(name = "EMAIL")
    String email

    User() {
    }

    User(String email) {
        this.email = email
    }
}
