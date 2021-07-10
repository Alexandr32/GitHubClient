package ru.appdevelopers.githubclient.ui.auth

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.appdevelopers.githubclient.model.IAuthCallback

interface AuthView: MvpView, IAuthCallback {
    @AddToEndSingle
    fun showMessage(message: String)
}