package ru.appdevelopers.githubclient.ui.repositoriesList

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.appdevelopers.githubclient.ui.Screens
import javax.inject.Inject

@InjectViewState
class ProjectsListPresenter @Inject constructor(private val router: Router) : MvpPresenter<RepositoriesListView>() {

    fun showMessage() {
        viewState?.showMessage("Назад")
    }

    fun onNextButtonClicked() = router.navigateTo(Screens.repositoriesList())
    fun onBackButtonClicked() = router.exit()
}