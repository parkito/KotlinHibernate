package ru.siksmfp.hibernate.entity

import ru.siksmfp.hibernate.extensions.EMPTY
import ru.siksmfp.hibernate.extensions.ZERO
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class PersonalData(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val userId: Long = Long.ZERO,

        @Column(nullable = false)
        val serial: String = String.EMPTY,

        @Column(nullable = false)
        val number: String = String.EMPTY,

        @OneToOne(fetch = FetchType.LAZY,
                cascade = arrayOf(CascadeType.ALL),
                mappedBy = "userProfile")
        val user: User = User()
)
