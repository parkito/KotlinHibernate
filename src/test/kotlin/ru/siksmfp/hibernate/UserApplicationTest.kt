package ru.siksmfp.hibernate

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import ru.siksmfp.hibernate.config.JpaTestConfig

@SpringBootTest
@ContextConfiguration(classes = arrayOf(JpaTestConfig::class))
open class UserApplicationTest