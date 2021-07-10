package ru.appdevelopers.githubclient.ui.auth

import android.content.Intent
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.ui.Screens
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor(
    private val router: Router,
    private var googleSignInClient: GoogleSignInClient
) : MvpPresenter<AuthView>() {


    fun goToListRepository() {
        router.newRootScreen(Screens.repositoriesListPresenter())
    }

    fun showMessage() {
        viewState?.showMessage("Вход")
    }

    fun getGoogleSignInIntent(): Intent {
       return googleSignInClient.signInIntent
    }

    fun googleAuth(data: Intent) {

    }

}