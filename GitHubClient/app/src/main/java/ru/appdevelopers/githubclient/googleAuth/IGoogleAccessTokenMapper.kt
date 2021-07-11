package ru.appdevelopers.githubclient.googleAuth

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.gson.annotations.SerializedName
import ru.appdevelopers.githubclient.models.AccessToken
import javax.inject.Inject

class GoogleAccessToken(
    accessToken: String? = null,
    username: String? = null
): AccessToken(accessToken, username)

interface IAccessTokenMapper {
    fun mapFromGoogleToModel(value: GoogleSignInAccount): GoogleAccessToken
}

class AccessTokenMapper @Inject constructor() : IAccessTokenMapper {
    override fun mapFromGoogleToModel(value: GoogleSignInAccount): GoogleAccessToken {
        return GoogleAccessToken(username = value.displayName)
    }
}