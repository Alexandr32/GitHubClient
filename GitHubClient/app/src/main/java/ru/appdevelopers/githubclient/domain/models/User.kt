package ru.appdevelopers.githubclient.domain.models

data class User(val type: AuthType, val userName: String? = null)