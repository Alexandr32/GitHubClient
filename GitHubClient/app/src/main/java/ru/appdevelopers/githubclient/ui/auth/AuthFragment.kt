package ru.appdevelopers.githubclient.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.auth_fragment.*
import moxy.presenter.InjectPresenter
import ru.appdevelopers.githubclient.R
import moxy.MvpAppCompatFragment
import moxy.presenter.ProvidePresenter
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.ui.activity.GitHubPresenter
import toothpick.Toothpick

class AuthFragment: MvpAppCompatFragment(), AuthView  {

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

    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
        toast.show()
    }
}