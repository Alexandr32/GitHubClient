package ru.appdevelopers.githubclient.domain.gitHub.model

import ru.appdevelopers.githubclient.domain.models.AccessToken

class GitHubAccessToken(
    accessToken: String? = null,
    username: String? = null
): AccessToken(accessToken, username)