package ru.appdevelopers.githubclient.ui.base

import android.widget.Toast
import moxy.MvpAppCompatFragment
import ru.appdevelopers.githubclient.model.IBaseCallback

abstract class BaseFragment: MvpAppCompatFragment(), IBaseCallback {
    override fun showProgress() {
        showMessage("showProgress")
    }

    override fun showLockUiProgress() {
        showMessage("showLockUiProgress")
    }

    override fun showSwipeProgress() {
        showMessage("showSwipeProgress")
    }

    override fun hideProgress(isError: Boolean) {
        showMessage("hideProgress")
    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
        toast.show()
    }

}