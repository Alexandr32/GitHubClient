package ru.appdevelopers.githubclient.ui.activity

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.ui.Screens
import javax.inject.Inject

@InjectViewState
class GitHubPresenter @Inject constructor(
    private val router: Router
): MvpPresenter<GitHubClientView?>() {

    fun coldStart() {
        router.newRootScreen(Screens.auth())
    }

    fun showMessage() {
        TODO("Функционал не реализован")
    }
}