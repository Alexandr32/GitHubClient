package ru.appdevelopers.githubclient

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.presenters.GitHubPresenter
import ru.appdevelopers.githubclient.ui.GitHubClientView
import ru.appdevelopers.githubclient.ui.auth.AuthFragment
import ru.appdevelopers.githubclient.ui.repositoriesList.RepositoriesListFragment
import toothpick.Toothpick


class MainActivity : MvpAppCompatActivity(), GitHubClientView {

    @InjectPresenter
    lateinit var gitHubPresenter: GitHubPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addScopeAppModule()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthFragment.newInstance())
                .commitNow()
        }



    }

    private fun addScopeAppModule() {
        val appScope = Toothpick.openScope(DIConfig.APP_SCOPE)
        Toothpick.inject(this, appScope)
    }

    override fun showMessage(message: String) {
        val snackbar = Snackbar.make(container, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}