package ru.appdevelopers.githubclient.services

import javax.inject.Inject

interface IGitHubApiService {
    fun isWork(): String
}

class GitHubApiService @Inject constructor(): IGitHubApiService {
    override fun isWork(): String = "isWork"
}