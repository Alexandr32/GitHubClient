package ru.appdevelopers.domain.models

sealed class AuthErrorResponse
class GoogleAuthErrorResponse<T>(val data: T) : AuthErrorResponse()
class GitHubAuthErrorResponse<T>(val data: T) : AuthErrorResponse()