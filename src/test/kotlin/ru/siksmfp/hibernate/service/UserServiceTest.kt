package ru.siksmfp.hibernate.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.siksmfp.hibernate.UserApplicationTest

class UserServiceTest : UserApplicationTest() {

    @Autowired
    lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userService.deleteAll()
        userService.saveAll(initUsers())
    }

    @Test
    fun addUserTest() {
        userService.createUser(email = "email1", firstName = "name1", secondName = "lastName1")
        val user = userService.findUserByEmail("email1")
        Assertions.assertNotNull(user)
    }

    @Test
    fun findUserTest() {
        val user = userService.findUserByEmail("email_1")
        Assertions.assertNotNull(user)
    }
}