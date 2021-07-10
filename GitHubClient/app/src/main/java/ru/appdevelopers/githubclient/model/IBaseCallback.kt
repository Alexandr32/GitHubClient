package ru.appdevelopers.githubclient.model

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface IBaseCallback : MvpView {
    @AddToEndSingle
    fun showProgress()
    @AddToEndSingle
    fun showLockUiProgress()
    @AddToEndSingle
    fun showSwipeProgress()
    @AddToEndSingle
    fun hideProgress(isError: Boolean = false)
    @AddToEndSingle
    fun showMessage(message: String)
}