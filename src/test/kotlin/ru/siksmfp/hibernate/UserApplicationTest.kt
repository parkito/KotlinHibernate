package ru.siksmfp.hibernate

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.siksmfp.hibernate.config.JpaTestConfig
import ru.siksmfp.hibernate.entity.User

@SpringBootTest
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = arrayOf(JpaTestConfig::class, UserApplication::class))
open class UserApplicationTest {

    fun initUsers(): List<User> {
        val user1: User = User(email = "email_1", firstName = "firstName_1", lastName = "lastName_1")
        val user2: User = User(email = "email_2", firstName = "firstName_2", lastName = "lastName_2")
        val user3: User = User(email = "email_3", firstName = "firstName_3", lastName = "lastName_3")
        val user4: User = User(email = "email_4", firstName = "firstName_4", lastName = "lastName_4")
        val user5: User = User(email = "email_5", firstName = "firstName_5", lastName = "lastName_5")
        val user6: User = User(email = "email_6", firstName = "firstName_6", lastName = "lastName_6")

        return listOf(user1)
    }

}