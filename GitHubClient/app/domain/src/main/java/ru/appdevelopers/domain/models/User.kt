package ru.appdevelopers.domain.models

data class User(val type: AuthType, val userName: String? = null)