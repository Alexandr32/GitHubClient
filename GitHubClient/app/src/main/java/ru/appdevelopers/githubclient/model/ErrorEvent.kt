package ru.appdevelopers.githubclient.model

import ru.appdevelopers.githubclient.googleAuth.GoogleAccessToken

class ErrorEvent(public val messageError: String): GoogleAccessToken() {
}