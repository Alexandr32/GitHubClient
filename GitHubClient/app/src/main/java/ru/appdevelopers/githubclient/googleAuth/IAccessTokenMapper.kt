package ru.appdevelopers.githubclient.googleAuth

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

open class GoogleAccessToken(
    val accessToken: String? = null,
    val username: String? = null
)

data class GoogleAccessTokenResponse(
    @SerializedName("access_token")
    val accessToken: String
)

interface IAccessTokenMapper {
    fun mapFromResponseToModel(value: GoogleAccessTokenResponse): GoogleAccessToken
    fun mapFromGoogleToModel(value: GoogleSignInAccount): GoogleAccessToken
}

class AccessTokenMapper @Inject constructor() : IAccessTokenMapper {

    override fun mapFromResponseToModel(value: GoogleAccessTokenResponse): GoogleAccessToken {
        return GoogleAccessToken(accessToken = value.accessToken)
    }

    override fun mapFromGoogleToModel(value: GoogleSignInAccount): GoogleAccessToken {
        return GoogleAccessToken(username = value.displayName)
    }
}