package ru.appdevelopers.githubclient.ui.auth

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.appdevelopers.githubclient.googleAuth.GoogleAccessToken
import ru.appdevelopers.githubclient.model.IBaseCallback

interface IAuthCallback : IBaseCallback {
    /**
     * Вход
     */
    @AddToEndSingle
    fun onAuthSuccess(accessToken: GoogleAccessToken)

    /**
     * Ошибка входа
     */
    @AddToEndSingle
    fun onAuthError(errorEvent: GoogleAccessToken)
}
