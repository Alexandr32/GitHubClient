package ru.appdevelopers.githubclient.gihHubService

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.Response
import ru.appdevelopers.githubclient.models.AccessToken
import ru.appdevelopers.githubclient.models.ApiResponse
import javax.inject.Inject

/**
 * Ответ с токеном авторизации через гитхаб
 */
data class GitHubAccessTokenResponse(
    @SerializedName("access_token")
    val accessToken: String
)

class GitHubAccessToken(
    accessToken: String? = null,
    username: String? = null
): AccessToken(accessToken, username)


interface IGitHubService {
    fun auth(code: String): Single<ApiResponse<GitHubAccessToken>>
}

class GitHubService @Inject constructor(
    private val api: IApi): IGitHubService {

    companion object {
        const val CLIENT_ID = ""
        const val CLIENT_SECRET = ""
    }

    override fun auth(code: String): Single<ApiResponse<GitHubAccessToken>> {
       return api.auth(CLIENT_ID, CLIENT_SECRET, code).map {
           return@map ApiResponse.create(it) { value ->
               GitHubAccessToken(accessToken = value.accessToken)
           }
       }
    }

}