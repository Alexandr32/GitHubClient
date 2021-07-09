package ru.appdevelopers.githubclient.ui.auth

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.di.DIConfig
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor() : MvpPresenter<AuthView>() {

    init {
        val appScope = Toothpick.openScope(DIConfig.APP_SCOPE)
        Toothpick.inject(this, appScope)
    }

    fun showMessage() {
        viewState?.showMessage("Вход")
    }

}