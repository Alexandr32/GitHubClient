package ru.appdevelopers.githubclient.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.appdevelopers.githubclient.ui.auth.AuthFragment
import ru.appdevelopers.githubclient.ui.repositoriesList.ProjectsListFragment

object Screens {
    fun auth() = FragmentScreen { AuthFragment() }
    fun repositoriesList() = FragmentScreen { ProjectsListFragment() }
}