package ru.appdevelopers.githubclient.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.auth_fragment.*
import moxy.presenter.InjectPresenter
import ru.appdevelopers.githubclient.R
import moxy.presenter.ProvidePresenter
import ru.appdevelopers.domain.models.AccessToken
import ru.appdevelopers.domain.models.AuthErrorResponse
import ru.appdevelopers.domain.models.GitHubAuthErrorResponse
import ru.appdevelopers.domain.models.GoogleAuthErrorResponse
import ru.appdevelopers.githubclient.ui.base.BaseFragment
import toothpick.Toothpick


class AuthFragment: BaseFragment(), IAuthCallback {

    companion object {
        const val GOOGLE_SIGN_IN = 9001
    }

    @InjectPresenter
    lateinit var authPresenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter(): AuthPresenter =
        Toothpick.openScope(ru.appdevelopers.di.DIConfig.APP_SCOPE).getInstance(AuthPresenter::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.auth_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonEntry.setOnClickListener {
            authPresenter.goToListRepository()
        }

        buttonSignInGoogle.setOnClickListener {
            val intent = authPresenter.getGoogleSignInIntent()
            startActivityForResult(intent, GOOGLE_SIGN_IN)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN && data != null) {
            authPresenter.googleAuth(data)
        }
    }

    override fun onAuthSuccess(accessToken: AccessToken) {
        authPresenter.showMessage("Вход выполнен успешно")
        authPresenter.goToListRepository()
    }

    override fun onAuthError(authErrorResponse: AuthErrorResponse) {
        if(authErrorResponse is GoogleAuthErrorResponse<*>) {
            authPresenter.showMessage("Авторизация через аккаунт гугл не удалась")
        }

        if (authErrorResponse is GitHubAuthErrorResponse<*>) {
            authPresenter.showMessage("Авторизация через аккаунт гит не удалась")
        }
    }
}