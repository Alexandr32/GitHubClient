package ru.appdevelopers.githubclient.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.appdevelopers.githubclient.ui.auth.AuthFragment

object Screens {
    fun login() = FragmentScreen { AuthFragment() }
}