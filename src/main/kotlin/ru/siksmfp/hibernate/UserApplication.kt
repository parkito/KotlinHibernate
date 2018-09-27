package ru.siksmfp.hibernate;

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
@SpringBootApplication
class UserApplication {

    @Bean
    fun restTemplate() = RestTemplate();

}

fun main(args: Array<String>) {
    SpringApplication.run(UserApplication::class.java, *args);
}

