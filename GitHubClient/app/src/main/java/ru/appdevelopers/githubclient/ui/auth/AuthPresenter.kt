package ru.appdevelopers.githubclient.ui.auth

import android.content.Intent
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.models.User
import ru.appdevelopers.githubclient.googleAuth.GoogleAccessToken
import ru.appdevelopers.githubclient.googleAuth.IAccessTokenMapper
import ru.appdevelopers.githubclient.models.AuthType
import ru.appdevelopers.githubclient.models.GoogleAuthErrorResponse
import ru.appdevelopers.githubclient.repository.IUserRepository
import ru.appdevelopers.githubclient.ui.Screens
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor(
    private val router: Router,
    private val googleSignInClient: GoogleSignInClient,
    private val accessTokenMapper: IAccessTokenMapper,
    private val userRepository: IUserRepository
) : MvpPresenter<IAuthCallback>() {


    fun goToListRepository() {
        router.newRootScreen(Screens.repositoriesListPresenter())
    }

    fun showMessage(message: String) {
        viewState?.showMessage(message)
    }

    fun getGoogleSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    fun googleAuth(data: Intent) {
        Single.create<GoogleAccessToken> { emitter ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            task.addOnSuccessListener {
                emitter.onSuccess(accessTokenMapper.mapFromGoogleToModel(it))
            }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLockUiProgress() }
            .doFinally { viewState.hideProgress() }
            .subscribeWith(object : DisposableSingleObserver<GoogleAccessToken>() {
                override fun onSuccess(value: GoogleAccessToken) {

                    val user = User(AuthType.GOOGLE, value.username)
                    userRepository.saveAuth(user)

                    viewState.onAuthSuccess(value)

                }

                override fun onError(error: Throwable) {
                    Log.e("githubclient", error.message.toString())

                    val googleAuthErrorResponse = GoogleAuthErrorResponse(error.message.toString())
                    viewState.onAuthError(googleAuthErrorResponse)
                }
            })
    }

}