package ru.appdevelopers.githubclient.ui.activity

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.services.IGitHubApiService
import ru.appdevelopers.githubclient.ui.Screens
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class GitHubPresenter @Inject constructor(
    private val router: Router,
    private val gitHubApiService: IGitHubApiService
): MvpPresenter<GitHubClientView?>() {

    fun coldStart() {
        router.newRootScreen(Screens.auth())
    }

    fun showMessage() {
        viewState?.showMessage(gitHubApiService.isWork())
    }
}