package ru.siksmfp.hibernate.exception

class SystemException : RuntimeException {
    var developerMessage: String = ""

    constructor(message: String?) : super(message)
}