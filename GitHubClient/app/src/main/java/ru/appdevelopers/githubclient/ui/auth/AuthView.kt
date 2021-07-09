package ru.appdevelopers.githubclient.ui.auth

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface AuthView: MvpView {
    @AddToEndSingle
    fun showMessage(message: String)
}