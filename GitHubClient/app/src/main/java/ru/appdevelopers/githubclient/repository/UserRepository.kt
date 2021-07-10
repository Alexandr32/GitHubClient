package ru.appdevelopers.githubclient.repository

import android.content.SharedPreferences
import javax.inject.Inject

interface IUserRepository {

}

class UserRepository @Inject constructor(sharedPreferences: SharedPreferences): IUserRepository {

}