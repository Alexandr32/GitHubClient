package ru.appdevelopers.githubclient.ui.repositoriesList

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.ui.Screens
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class RepositoriesListPresenter @Inject constructor(private val router: Router) : MvpPresenter<RepositoriesListView>() {

    fun showMessage() {
        viewState?.showMessage("Назад")
    }

    fun back() {
        router.newRootScreen(Screens.auth())
    }
}