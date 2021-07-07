package ru.appdevelopers.githubclient.di

import android.content.Context
import android.content.SharedPreferences
import ru.appdevelopers.githubclient.services.GitHubApiService
import ru.appdevelopers.githubclient.services.IGitHubApiService
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        bindSharedPreferences(context)
        bindGitHubApiService()
    }

    private fun bindSharedPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences(DIConfig.APP_SCOPE, Context.MODE_PRIVATE)
        bind(SharedPreferences::class.java).toInstance(sharedPreferences)
    }

    private fun bindGitHubApiService() {
        bind(IGitHubApiService::class.java)
            .to(GitHubApiService::class.java)
            .singleton()
    }

}