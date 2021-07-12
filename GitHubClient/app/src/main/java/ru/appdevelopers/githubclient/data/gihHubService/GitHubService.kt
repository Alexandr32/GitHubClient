package ru.appdevelopers.githubclient.data.gihHubService

import io.reactivex.Single
import ru.appdevelopers.githubclient.domain.gitHub.model.GitHubAccessToken
import ru.appdevelopers.githubclient.domain.gitHub.IGitHubService
import ru.appdevelopers.githubclient.domain.models.ApiResponse
import javax.inject.Inject


class GitHubService @Inject constructor(
    private val api: IApi): IGitHubService {

    companion object {
        const val CLIENT_ID = ""
        const val CLIENT_SECRET = ""
    }

    override fun auth(code: String): Single<ApiResponse<GitHubAccessToken>> {
       return api.auth(CLIENT_ID, CLIENT_SECRET, code).map {
           return@map ApiResponseMapper.map(it) { value ->
               GitHubAccessToken(accessToken = value.accessToken)
           }
       }
    }

}