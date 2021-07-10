package ru.appdevelopers.githubclient.ui.auth

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.ui.Screens
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor(private val router: Router) : MvpPresenter<AuthView>() {


    fun goToListRepository() {
        router.newRootScreen(Screens.repositoriesListPresenter())
    }

    fun showMessage() {
        viewState?.showMessage("Вход")
    }

}