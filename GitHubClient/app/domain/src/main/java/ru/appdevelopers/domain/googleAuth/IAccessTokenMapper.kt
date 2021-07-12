package ru.appdevelopers.domain.googleAuth

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import ru.appdevelopers.domain.googleAuth.model.GoogleAccessToken

interface IAccessTokenMapper {
    fun mapFromGoogleToModel(value: GoogleSignInAccount): GoogleAccessToken
}