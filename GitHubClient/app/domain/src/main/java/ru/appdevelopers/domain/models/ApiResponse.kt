package ru.appdevelopers.domain.models

/**
 * Класс для мапинга полученных данных и обработки ответов апи
 */
sealed class ApiResponse<T>
class ApiSuccessResponse<T>(val data: T) : ApiResponse<T>()
open class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()
class ApiAccessDeniedResponse<T>: ApiErrorResponse<T>("Доступ запрещен")
