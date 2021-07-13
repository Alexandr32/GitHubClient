package ru.appdevelopers.domain.googleAuth.model

import ru.appdevelopers.domain.models.AccessToken

class GoogleAccessToken(
    accessToken: String? = null,
    username: String? = null
): AccessToken(accessToken, username)