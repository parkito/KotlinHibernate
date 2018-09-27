package ru.siksmfp.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.siksmfp.hibernate.entity.User
import ru.siksmfp.hibernate.exception.SystemException
import ru.siksmfp.hibernate.repository.UserRepository

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Transactional
    fun createUser(email: String, firstName: String, secondName: String): User {
        val existedUser: User? = userRepository.findByEmail(email);
        if (existedUser == null) {
            val user = User()

            return userRepository.save(user)
        } else {
            throw SystemException("User with email = $email already exists")
        }
    }

}
