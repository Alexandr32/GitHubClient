package ru.appdevelopers.githubclient.models


enum class AuthType(val value: Int) {
    NONE(1),
    GIT_HUB(2),
    GOOGLE(3);
}

fun Int.toAuthType(): AuthType {
    if (this < 1 || this > 3) {
        throw IllegalArgumentException("Значение выходит за рамки enum")
    }

    return when(this) {
        2 -> {
            AuthType.GIT_HUB
        }
        3 -> {
            AuthType.GOOGLE
        }
        else -> AuthType.NONE
    }
}

