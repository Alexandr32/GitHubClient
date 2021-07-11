package ru.appdevelopers.githubclient.models

import retrofit2.Response

/**
 * Класс для мапинга полученных данных и обработки ответов апи
 */
sealed class ApiResponse<T> {
    companion object {

        fun <T, V> create(response: Response<T>, toMap: (T) -> V): ApiResponse<V> {

            return if (response.isSuccessful) {
                // isSuccessful - code 200..300
                try {
                    isSuccessfulResponse(response, toMap)
                } catch (e: Exception) {
                    ApiErrorResponse("При обработке полученных данных произошла ошибка: ${e.message}")
                }
            } else {
                if (response.code() == 403) {
                    ApiAccessDeniedResponse()
                } else {
                    failResponse(response)
                }
            }
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return create(response) { it }
        }

        /**
         * Обработка статусов 200..300
         * */
        private fun <T, V> isSuccessfulResponse(
            response: Response<T>,
            toMap: (T) -> V
        ): ApiResponse<V> {
            val body = response.body()
            return when {
                body == null || response.code() == 204 -> {
                    ApiErrorResponse("Пустой ответ")
                }
                else -> {
                    val convertBody = toMap.invoke(body)
                    ApiSuccessResponse(convertBody)
                }
            }
        }

        /**
         * Обработка статусов с ошибками
         * */
        private fun <T, V> failResponse(response: Response<T>): ApiResponse<V> {
            val msg = response.errorBody()?.string()
            val errorMessage = if (msg.isNullOrEmpty()) {
                response.message()
            } else {
                msg
            }
            return ApiErrorResponse(errorMessage ?: "Что-то пошло не так")
        }
    }
}

class ApiSuccessResponse<T>(val data: T) : ApiResponse<T>()
open class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()
class ApiAccessDeniedResponse<T>: ApiErrorResponse<T>("Доступ запрещен")
