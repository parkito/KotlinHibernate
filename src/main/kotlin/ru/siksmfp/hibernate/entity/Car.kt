package ru.siksmfp.hibernate.entity

import ru.siksmfp.hibernate.extensions.EMPTY
import ru.siksmfp.hibernate.extensions.ZERO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
data class Car(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = Long.ZERO,

        @Column(nullable = false, unique = true)
        var model: String = String.EMPTY,

        @Column(nullable = false)
        var age: String = String.EMPTY,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        var user: User? = null

)