package ru.appdevelopers.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.appdevelopers.githubclient.di.AppModule
import ru.appdevelopers.githubclient.di.DIConfig
import ru.appdevelopers.githubclient.services.GitHubApiService
import ru.appdevelopers.githubclient.services.IGitHubApiService
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var gitHubApiService: IGitHubApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addScopeAppModule()

        Log.d("githubclient",gitHubApiService.isWork())
    }

    private fun addScopeAppModule() {
        val appScope = Toothpick.openScope(DIConfig.APP_SCOPE)
        Toothpick.inject(this, appScope)
    }
}