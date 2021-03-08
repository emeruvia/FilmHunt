package dev.emg.filmhunt.data

object Utils {

  private const val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"

  fun getFullImageUrl(imagePath: String?) = "$imageBaseUrl$imagePath"

}