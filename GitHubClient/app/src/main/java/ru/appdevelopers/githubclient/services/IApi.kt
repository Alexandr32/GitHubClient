package ru.appdevelopers.githubclient.services

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<String>>
}