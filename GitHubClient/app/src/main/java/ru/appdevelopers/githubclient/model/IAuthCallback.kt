package ru.appdevelopers.githubclient.model

import ru.appdevelopers.githubclient.googleAuth.GoogleAccessToken

interface IAuthCallback : IBaseCallback {
    /**
     * Вход
     */
    fun onAuthSuccess(accessToken: GoogleAccessToken)

    /**
     * Ошибка входа
     */
    fun onAuthError(errorEvent: GoogleAccessToken)
}