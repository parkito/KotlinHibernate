package ru.siksmfp.hibernate.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.siksmfp.hibernate.UserApplicationTest

@ExtendWith(SpringExtension::class)
class UserServiceTest : UserApplicationTest() {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun addUserTest() {
        userService.createUser(email = "email1", firstName = "name1", secondName = "lastName1")

        val user = userService.findUserByEmail("email1")

        Assertions.assertNotNull(user)
    }
}