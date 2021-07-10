package ru.appdevelopers.githubclient.di

import android.content.Context
import android.content.SharedPreferences
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import ru.appdevelopers.githubclient.googleAuth.AccessTokenMapper
import ru.appdevelopers.githubclient.googleAuth.IAccessTokenMapper
import ru.appdevelopers.githubclient.repository.IUserRepository
import ru.appdevelopers.githubclient.repository.UserRepository
import ru.appdevelopers.githubclient.services.GitHubApiService
import ru.appdevelopers.githubclient.services.IApi
import ru.appdevelopers.githubclient.services.IGitHubApiService
import toothpick.config.Module


class AppModule(context: Context) : Module() {
    init {
        bindCicerone()
        bindSharedPreferences(context)
        bindUserRepository()
        bindAccessTokenMapper()
        bindGoogleSignIn(context)
        bindGitHubApiService()
    }

    private fun bindSharedPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences(DIConfig.APP_SCOPE, Context.MODE_PRIVATE)
        bind(SharedPreferences::class.java).toInstance(sharedPreferences)
    }

    private fun bindGitHubApiService() {

        val contextApi = IApi.create()

        bind(IApi::class.java)
            .toInstance(contextApi)

        bind(IGitHubApiService::class.java)
            .to(GitHubApiService::class.java)
            .singleton()
    }

    private fun bindCicerone() {
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.getNavigatorHolder())
    }

    private fun bindGoogleSignIn(context: Context) {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        bind(GoogleSignInClient::class.java).toInstance(googleSignInClient)
    }

    private fun bindAccessTokenMapper() {
        bind(IAccessTokenMapper::class.java)
            .to(AccessTokenMapper::class.java)
            .singleton()
    }

    private fun bindUserRepository() {

        bind(IUserRepository::class.java)
            .to(UserRepository::class.java)
            .singleton()
    }
}