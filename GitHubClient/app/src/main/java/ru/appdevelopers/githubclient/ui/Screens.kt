package ru.appdevelopers.githubclient.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.appdevelopers.githubclient.ui.auth.AuthFragment
import ru.appdevelopers.githubclient.ui.repositoriesList.RepositoriesListFragment
import ru.appdevelopers.githubclient.ui.repositoriesList.RepositoriesListPresenter

object Screens {
    fun auth() = FragmentScreen { AuthFragment() }
    fun repositoriesListPresenter() = FragmentScreen { RepositoriesListFragment() }
}