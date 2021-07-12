package ru.appdevelopers.domain.gitHub

import io.reactivex.Single
import ru.appdevelopers.domain.gitHub.model.GitHubAccessToken
import ru.appdevelopers.domain.models.ApiResponse


interface IGitHubService {
    fun auth(code: String): Single<ApiResponse<GitHubAccessToken>>
}