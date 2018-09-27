package ru.siksmfp.hibernate

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.siksmfp.hibernate.config.JpaTestConfig

@SpringBootTest
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = arrayOf(JpaTestConfig::class, UserApplication::class))
open class UserApplicationTest {

}