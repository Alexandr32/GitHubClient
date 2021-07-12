package ru.appdevelopers.githubclient.ui.repositoriesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.repository_list_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.appdevelopers.githubclient.R
import ru.appdevelopers.di.DIConfig
import toothpick.Toothpick

class RepositoriesListFragment: MvpAppCompatFragment(), RepositoriesListView {

    @InjectPresenter
    lateinit var repositoriesListPresenter: RepositoriesListPresenter

    @ProvidePresenter
    fun providePresenter(): RepositoriesListPresenter =
        Toothpick.openScope(ru.appdevelopers.di.DIConfig.APP_SCOPE).getInstance(RepositoriesListPresenter::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repository_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener {
            repositoriesListPresenter.onNextButtonClicked()
        }

        buttonBack.setOnClickListener {
            repositoriesListPresenter.onBackButtonClicked()
        }

    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
        toast.show()
    }
}