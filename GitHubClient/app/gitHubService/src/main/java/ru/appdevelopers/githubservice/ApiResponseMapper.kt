package ru.appdevelopers.githubservice

import retrofit2.Response
import ru.appdevelopers.domain.models.ApiAccessDeniedResponse
import ru.appdevelopers.domain.models.ApiErrorResponse
import ru.appdevelopers.domain.models.ApiResponse
import ru.appdevelopers.domain.models.ApiSuccessResponse

/**
 * Класс для мапинга полученных данных и обработки ответов апи
 */
class ApiResponseMapper {
    companion object {
        fun <T, V> map(response: Response<T>, toMap: (T) -> V): ApiResponse<V> {

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

        fun <T> map(response: Response<T>): ApiResponse<T> {
            return map(response) { it }
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