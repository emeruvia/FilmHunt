package dev.emg.filmhunt.data.vo

import com.google.gson.annotations.SerializedName

data class MovieResponse(
  val page: Int,
  val results: List<Movie>,
  @SerializedName("total_results") val totalResults: Int,
  @SerializedName("total_pages") val totalPages: Int
)