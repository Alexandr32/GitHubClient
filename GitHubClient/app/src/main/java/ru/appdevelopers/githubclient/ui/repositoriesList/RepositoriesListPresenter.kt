package ru.appdevelopers.githubclient.ui.repositoriesList

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.di.DIConfig
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class RepositoriesListPresenter @Inject constructor() : MvpPresenter<RepositoriesListView>() {
    init {
        val appScope = Toothpick.openScope(DIConfig.APP_SCOPE)
        Toothpick.inject(this, appScope)
    }

    fun showMessage() {
        viewState?.showMessage("Назад")
    }
}