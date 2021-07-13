package ru.appdevelopers.domain.models

/**
 * Обертка для ответа апи
 */
sealed class ApiResponse<T>
class ApiSuccessResponse<T>(val data: T) : ApiResponse<T>()
open class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()
class ApiAccessDeniedResponse<T>: ApiErrorResponse<T>("Доступ запрещен")
