package dev.emg.filmhunt.data

fun String.toFullImageUrl(): String {
  val baseUrl = "https://image.tmdb.org/t/p/w500/"
  return "$baseUrl$this"
}