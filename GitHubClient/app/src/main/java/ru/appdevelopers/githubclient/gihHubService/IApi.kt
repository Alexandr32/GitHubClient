package ru.appdevelopers.githubclient.gihHubService

import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query
import ru.appdevelopers.githubclient.BuildConfig

interface IApi {

    companion object {
        fun create(): IApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API)
                .build()

            return retrofit.create(IApi::class.java)
        }
    }

    @POST("login/oauth/access_token")
    fun auth(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String
    ): Single<Response<GitHubAccessTokenResponse>>
}