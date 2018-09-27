package ru.siksmfp.hibernate.entity

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import ru.siksmfp.hibernate.extensions.EMPTY
import ru.siksmfp.hibernate.extensions.ZERO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.PrimaryKeyJoinColumn

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        var id: Long = Long.ZERO,

        @Column(nullable = false, unique = true)
        var email: String = String.EMPTY,

        @Column(nullable = false)
        var firstName: String = String.EMPTY,

        @Column(nullable = false)
        var lastName: String = String.EMPTY,

        @OneToOne(fetch = FetchType.LAZY)
        @PrimaryKeyJoinColumn
        var personalData: PersonalData = PersonalData(),

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        @OnDelete(action = OnDeleteAction.CASCADE)
        var cars: List<Car> = ArrayList()
)