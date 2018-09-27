package ru.siksmfp.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.siksmfp.hibernate.entity.User
import ru.siksmfp.hibernate.exception.SystemException
import ru.siksmfp.hibernate.repository.UserRepository

/**
 * @author Artem Karnov @date 11/6/2017.
 * artem.karnov@t-systems.com
 */
@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository


    @Transactional
    fun createUser(email: String, firstName: String, secondName: String): User {
        val existedUser: User? = userRepository.findByEmail(email);
        if (existedUser == null) {
            val user = User(email = email, firstName = firstName, lastName = secondName)
            return userRepository.save(user)
        } else {
            throw SystemException("User with email = $email already exists")
        }
    }

}
