package ru.appdevelopers.githubclient.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface GitHubClientView: MvpView {
    @AddToEndSingle
    fun showMessage(message: String)
}