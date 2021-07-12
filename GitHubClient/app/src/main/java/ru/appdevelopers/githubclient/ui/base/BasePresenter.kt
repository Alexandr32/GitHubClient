package ru.appdevelopers.githubclient.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.ui.IBaseCallback

abstract class BasePresenter<T : IBaseCallback> : MvpPresenter<T>()  {

    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.disposeLater() {
        compositeDisposable.add(this)
    }

}