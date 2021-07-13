package ru.appdevelopers.githubclient.ui.repositoriesList

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface RepositoriesListView: MvpView {
    @AddToEndSingle
    fun showMessage(message: String)
}