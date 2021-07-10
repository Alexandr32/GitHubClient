package ru.appdevelopers.githubclient.model

import moxy.MvpView

interface IBaseCallback : MvpView {
    fun showProgress()
    fun showLockUiProgress()
    fun showSwipeProgress()
    fun hideProgress(isError: Boolean = false)
}