package dev.emg.filmhunt.data.vo

sealed class DataResult<out T> {

  data class Success<out T>(val data: T): DataResult<T>()

  object Loading: DataResult<Nothing>()

  data class Error(val throwable: Throwable, val msg: String?): DataResult<Nothing>()

}