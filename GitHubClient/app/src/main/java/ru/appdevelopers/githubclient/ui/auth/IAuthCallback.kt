package ru.appdevelopers.githubclient.ui.auth

import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.appdevelopers.githubclient.domain.models.AccessToken
import ru.appdevelopers.githubclient.domain.models.AuthErrorResponse
import ru.appdevelopers.githubclient.ui.IBaseCallback

interface IAuthCallback : IBaseCallback {
    /**
     * Вход
     */
    @AddToEndSingle
    fun onAuthSuccess(accessToken: AccessToken)

    /**
     * Ошибка входа
     */
    @AddToEndSingle
    fun onAuthError(authErrorResponse: AuthErrorResponse)
}
