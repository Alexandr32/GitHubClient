package ru.appdevelopers.domain.repository

import ru.appdevelopers.domain.models.User

interface IUserRepository {
    fun saveAuth(user: User)
    fun getAuth(): User?
}