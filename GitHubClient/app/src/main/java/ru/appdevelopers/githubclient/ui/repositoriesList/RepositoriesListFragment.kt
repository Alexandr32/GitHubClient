package ru.appdevelopers.githubclient.ui.repositoriesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.repository_list_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.appdevelopers.githubclient.R

class RepositoriesListFragment: MvpAppCompatFragment(), RepositoriesListView {

    companion object {
        fun newInstance() = RepositoriesListFragment()
    }

    @InjectPresenter
    lateinit var repositoriesListPresenter: RepositoriesListPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repository_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonBack.setOnClickListener {
            repositoriesListPresenter.showMessage()
        }

    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
        toast.show()
    }
}