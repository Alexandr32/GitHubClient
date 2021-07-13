package ru.appdevelopers.domain.gitHub.model

import com.google.gson.annotations.SerializedName

/**
 * Ответ с токеном авторизации через гитхаб
 */
data class GitHubAccessTokenResponse(
    @SerializedName("access_token")
    val accessToken: String
)