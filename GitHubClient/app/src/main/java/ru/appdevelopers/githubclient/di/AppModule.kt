package ru.appdevelopers.githubclient.di

import android.content.Context
import android.content.SharedPreferences
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        bindSharedPreferences(context)
    }

    private fun bindSharedPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences(DIConfig.APP_SCOPE, Context.MODE_PRIVATE)
        bind(SharedPreferences::class.java).toInstance(sharedPreferences)
    }
}