package ru.appdevelopers.githubclient

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.presenters.GitHubPresenter
import ru.appdevelopers.githubclient.views.GitHubClientView
import toothpick.Toothpick


class MainActivity : MvpAppCompatActivity(), GitHubClientView {

    @InjectPresenter
    lateinit var gitHubPresenter: GitHubPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addScopeAppModule()

        button.setOnClickListener {
            gitHubPresenter.showMessage()
        }

    }

    private fun addScopeAppModule() {
        val appScope = Toothpick.openScope(DIConfig.APP_SCOPE)
        Toothpick.inject(this, appScope)
    }

    override fun showMessage(message: String) {
        val snackbar = Snackbar.make(constraint_layout, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}