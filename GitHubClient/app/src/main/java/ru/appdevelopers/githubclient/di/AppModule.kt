package ru.appdevelopers.githubclient.di

import android.content.Context
import android.content.SharedPreferences
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import ru.appdevelopers.githubclient.services.GitHubApiService
import ru.appdevelopers.githubclient.services.IApi
import ru.appdevelopers.githubclient.services.IGitHubApiService
import toothpick.config.Module


class AppModule(context: Context) : Module() {
    init {
        bindCicerone()
        bindSharedPreferences(context)
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
}