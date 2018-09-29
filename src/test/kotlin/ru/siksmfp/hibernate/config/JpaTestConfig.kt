package ru.siksmfp.hibernate.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan("ru.siksmfp.hibernate.entity")
class JpaTestConfig {

}