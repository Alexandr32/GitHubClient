package ru.appdevelopers.githubclient.domain.googleAuth.model

import ru.appdevelopers.githubclient.domain.models.AccessToken

class GoogleAccessToken(
    accessToken: String? = null,
    username: String? = null
): AccessToken(accessToken, username)