package ru.appdevelopers.domain.gitHub.model

import ru.appdevelopers.domain.models.AccessToken

class GitHubAccessToken(
    accessToken: String? = null,
    username: String? = null
): AccessToken(accessToken, username)