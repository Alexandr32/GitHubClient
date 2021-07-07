package ru.appdevelopers.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.appdevelopers.githubclient.di.AppModule
import ru.appdevelopers.githubclient.di.DIConfig
import toothpick.Toothpick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addScopeAppModule()
    }

    private fun addScopeAppModule() {
        val appScope = Toothpick.openScope(DIConfig.APP_SCOPE)
        Toothpick.inject(this, appScope)
    }
}