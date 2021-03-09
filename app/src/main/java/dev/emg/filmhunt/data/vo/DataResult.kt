package dev.emg.filmhunt.data.vo

import java.lang.Exception

sealed class DataResult<out T> {

  data class Success<out T>(val data: T): DataResult<T>()

  object Loading: DataResult<Nothing>()

  data class Error(val e: Exception, val msg: String?): DataResult<Nothing>()

}