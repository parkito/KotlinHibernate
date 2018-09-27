package ru.siksmfp.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.siksmfp.hibernate.entity.User

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User
    fun deleteByEmail(email: String): Int;
}
