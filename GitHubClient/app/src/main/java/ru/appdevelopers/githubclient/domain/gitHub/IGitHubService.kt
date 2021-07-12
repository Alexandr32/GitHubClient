package ru.appdevelopers.githubclient.domain.gitHub

import io.reactivex.Single
import ru.appdevelopers.githubclient.domain.gitHub.model.GitHubAccessToken
import ru.appdevelopers.githubclient.domain.models.ApiResponse


interface IGitHubService {
    fun auth(code: String): Single<ApiResponse<GitHubAccessToken>>
}