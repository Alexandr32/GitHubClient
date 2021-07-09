package ru.appdevelopers.githubclient.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.services.IGitHubApiService
import ru.appdevelopers.githubclient.ui.GitHubClientView
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class GitHubPresenter: MvpPresenter<GitHubClientView?>() {

    @Inject
    lateinit var gitHubApiService: IGitHubApiService

    init {
        val appScope = Toothpick.openScope(DIConfig.APP_SCOPE)
        Toothpick.inject(this, appScope)
    }

    fun showMessage() {
        viewState?.showMessage(gitHubApiService.isWork())
    }
}