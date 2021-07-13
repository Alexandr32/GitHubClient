package ru.appdevelopers.githubclient.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.appdevelopers.githubclient.R
import ru.appdevelopers.di.DIConfig
import toothpick.Toothpick
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), GitHubClientView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var gitHubPresenter: GitHubPresenter

    @ProvidePresenter
    fun providePresenter(): GitHubPresenter =
        Toothpick.openScope(ru.appdevelopers.di.DIConfig.APP_SCOPE).getInstance(GitHubPresenter::class.java)

    private val navigator: Navigator =
        object : AppNavigator(this, R.id.container, supportFragmentManager) {
            override fun setupFragmentTransaction(
                screen: FragmentScreen,
                fragmentTransaction: FragmentTransaction,
                currentFragment: Fragment?,
                nextFragment: Fragment
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScope(ru.appdevelopers.di.DIConfig.APP_SCOPE))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gitHubPresenter.coldStart()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun showMessage(message: String) {
        val snackbar = Snackbar.make(container, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}