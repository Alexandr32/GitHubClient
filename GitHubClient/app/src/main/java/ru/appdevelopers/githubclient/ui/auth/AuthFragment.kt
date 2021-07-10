package ru.appdevelopers.githubclient.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.auth_fragment.*
import moxy.presenter.InjectPresenter
import ru.appdevelopers.githubclient.R
import moxy.MvpAppCompatFragment
import moxy.presenter.ProvidePresenter
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.ui.activity.GitHubPresenter
import toothpick.Toothpick
import javax.inject.Inject

class AuthFragment: MvpAppCompatFragment(), AuthView  {

    companion object {
        const val GOOGLE_SIGN_IN = 9001
    }

    //@Inject
    //lateinit var googleSignInClient: GoogleSignInClient

    @InjectPresenter
    lateinit var authPresenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter(): AuthPresenter =
        Toothpick.openScope(DIConfig.APP_SCOPE).getInstance(AuthPresenter::class.java)

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

    override fun showMessage(message: String) {
        val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
        toast.show()
    }
}