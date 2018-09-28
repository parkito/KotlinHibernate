package ru.siksmfp.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.siksmfp.hibernate.entity.Car
import ru.siksmfp.hibernate.entity.PersonalData
import ru.siksmfp.hibernate.entity.User
import ru.siksmfp.hibernate.exception.SystemException
import ru.siksmfp.hibernate.repository.UserRepository

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun findUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    @Transactional
    fun createUser(email: String, firstName: String, secondName: String): User {
        val existedUser: User? = userRepository.findByEmail(email);
        if (existedUser == null) {
            return userRepository.save(
                    User(firstName = firstName, lastName = secondName)
            )
        } else {
            throw SystemException("User with email = $email already exists")
        }
    }

    @Transactional
    fun addPersonalDataToUser(email: String, personalData: PersonalData) {
        val user: User? = userRepository.findByEmail(email)
        if (user != null) {
            user.personalData = personalData
            userRepository.save(user)
        } else {
            throw SystemException("User with email = $email doesn't exist")
        }
    }

    fun addCarToUser(email: String, cars: List<Car>) {
        val user: User? = userRepository.findByEmail(email)
        if (user != null) {
            user.cars = cars
            userRepository.save(user)
        } else {
            throw SystemException("User with email = $email doesn't exist")
        }
    }
}
