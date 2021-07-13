package ru.appdevelopers.githubservice

import io.reactivex.Single
import ru.appdevelopers.domain.gitHub.model.GitHubAccessToken
import ru.appdevelopers.domain.gitHub.IGitHubService
import ru.appdevelopers.domain.models.ApiResponse
import javax.inject.Inject


class GitHubService @Inject constructor(
    private val api: IApi
): IGitHubService {

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