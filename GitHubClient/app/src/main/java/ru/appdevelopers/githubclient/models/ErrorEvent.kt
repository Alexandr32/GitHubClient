package ru.appdevelopers.githubclient.models

sealed class AuthErrorResponse
class GoogleAuthErrorResponse<T>(val data: T) : AuthErrorResponse()
class GitHubAuthErrorResponse<T>(val data: T) : AuthErrorResponse()