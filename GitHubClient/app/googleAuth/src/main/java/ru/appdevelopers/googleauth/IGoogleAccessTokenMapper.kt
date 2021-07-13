package ru.appdevelopers.googleauth

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import ru.appdevelopers.domain.googleAuth.model.GoogleAccessToken
import ru.appdevelopers.domain.googleAuth.IAccessTokenMapper
import javax.inject.Inject

class AccessTokenMapper @Inject constructor() : IAccessTokenMapper {
    override fun mapFromGoogleToModel(value: GoogleSignInAccount): GoogleAccessToken {
        return GoogleAccessToken(username = value.displayName)
    }
}