package ru.siksmfp.hibernate.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val userId: Long = -1,

        @Column(nullable = false, unique = true)
        val email: String = "",

        @Column(nullable = false)
        val firstName: String = "",

        @Column(nullable = false)
        val lastName: String = ""
)