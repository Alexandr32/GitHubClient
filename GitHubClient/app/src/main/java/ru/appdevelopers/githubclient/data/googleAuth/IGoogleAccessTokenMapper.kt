package ru.appdevelopers.githubclient.data.googleAuth

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import ru.appdevelopers.githubclient.domain.googleAuth.model.GoogleAccessToken
import ru.appdevelopers.githubclient.domain.googleAuth.IAccessTokenMapper
import javax.inject.Inject

class AccessTokenMapper @Inject constructor() : IAccessTokenMapper {
    override fun mapFromGoogleToModel(value: GoogleSignInAccount): GoogleAccessToken {
        return GoogleAccessToken(username = value.displayName)
    }
}