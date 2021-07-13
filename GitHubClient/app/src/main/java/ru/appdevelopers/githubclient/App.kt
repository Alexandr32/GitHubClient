package ru.appdevelopers.githubclient

import android.app.Application
import ru.appdevelopers.di.AppModule
import ru.appdevelopers.di.DIConfig
import toothpick.Toothpick

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        addScopeAppModule()
    }

    private fun addScopeAppModule() {
        val appScope = Toothpick.openScope(ru.appdevelopers.di.DIConfig.APP_SCOPE)
        appScope.installModules(ru.appdevelopers.di.AppModule(applicationContext))
    }
}