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
import ru.appdevelopers.dal.repository.IUserRepository
import ru.appdevelopers.domain.models.*
import ru.appdevelopers.domain.gitHub.IGitHubService
import ru.appdevelopers.domain.gitHub.model.GitHubAccessToken
import ru.appdevelopers.domain.googleAuth.IAccessTokenMapper
import ru.appdevelopers.domain.googleAuth.model.GoogleAccessToken
import ru.appdevelopers.githubclient.ui.Screens
import ru.appdevelopers.githubclient.ui.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor(
    private val router: Router,
    private val googleSignInClient: GoogleSignInClient,
    private val gitHubService: IGitHubService,
    private val accessTokenMapper: IAccessTokenMapper,
    private val userRepository: IUserRepository
) : BasePresenter<IAuthCallback>() {

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
            }.addOnFailureListener {
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
                    onAuthError(googleAuthErrorResponse)
                }
            })
            .disposeLater()
    }

    fun gitHubAuth(code: String) {
        gitHubService.auth(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLockUiProgress() }
            .doFinally { viewState.hideProgress() }
            .subscribeWith(object : DisposableSingleObserver<ApiResponse<GitHubAccessToken>>() {
                override fun onSuccess(response: ApiResponse<GitHubAccessToken>) {

                    when (response) {
                        is ApiSuccessResponse -> {

                            // Вход успешен
                            val user = User(AuthType.GIT_HUB, response.data.username)
                            userRepository.saveAuth(user)

                            viewState.onAuthSuccess(response.data)

                        }
                        is ApiAccessDeniedResponse -> {
                            onGitHubAuthError("Доступ запрещен")
                        }
                        is ApiErrorResponse -> {
                            onGitHubAuthError(response.errorMessage)
                        }
                    }
                }

                override fun onError(error: Throwable) {
                    Log.e("githubclient", error.message.toString())

                    onGitHubAuthError(error.message.toString())
                }
            })
            .disposeLater()
    }

    private fun onGitHubAuthError(errorMessage: String) {
        val gitHubErrorResponse = GitHubAuthErrorResponse(errorMessage)
        onAuthError(gitHubErrorResponse)
    }

    private fun onAuthError(authErrorResponse: AuthErrorResponse) {
        viewState.onAuthError(authErrorResponse)
    }

}