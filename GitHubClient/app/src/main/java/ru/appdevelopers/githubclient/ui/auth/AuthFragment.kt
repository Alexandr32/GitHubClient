package ru.appdevelopers.githubclient.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import moxy.presenter.InjectPresenter
import ru.appdevelopers.githubclient.R
import moxy.MvpAppCompatFragment

class AuthFragment: MvpAppCompatFragment(), AuthView  {

    companion object {
        fun newInstance() = AuthFragment()
    }

    @InjectPresenter
    lateinit var authPresenter: AuthPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.auth_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authPresenter.showMessage()
    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
        toast.show()
    }
}