package ru.appdevelopers.githubclient

import android.app.Application
import ru.appdevelopers.githubclient.di.AppModule
import ru.appdevelopers.githubclient.di.DIConfig
import toothpick.Toothpick

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        addScopeAppModule()
    }

    private fun addScopeAppModule() {
        val appScope = Toothpick.openScope(DIConfig.APP_SCOPE)
        appScope.installModules(AppModule(applicationContext))
    }
}