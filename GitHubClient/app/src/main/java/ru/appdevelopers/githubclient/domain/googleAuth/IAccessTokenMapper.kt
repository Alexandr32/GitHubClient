package ru.appdevelopers.githubclient.domain.googleAuth

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import ru.appdevelopers.githubclient.domain.googleAuth.model.GoogleAccessToken

interface IAccessTokenMapper {
    fun mapFromGoogleToModel(value: GoogleSignInAccount): GoogleAccessToken
}