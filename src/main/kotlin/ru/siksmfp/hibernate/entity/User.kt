package ru.siksmfp.hibernate.entity

import ru.siksmfp.hibernate.extensions.EMPTY
import ru.siksmfp.hibernate.extensions.ZERO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.PrimaryKeyJoinColumn

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        var userId: Long = Long.ZERO,

        @Column(nullable = false, unique = true)
        var email: String = String.EMPTY,

        @Column(nullable = false)
        var firstName: String = String.EMPTY,

        @Column(nullable = false)
        var lastName: String = String.EMPTY,

        @OneToOne(fetch = FetchType.LAZY)
        @PrimaryKeyJoinColumn
        var personalData: PersonalData = PersonalData()
)