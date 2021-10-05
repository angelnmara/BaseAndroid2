package com.lamarrulla.mytiendita.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Res<out T : Any> {

    data class Success<out T : Any>(val data: T) : Res<T>()
    data class Error(val exception: Exception) : Res<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}