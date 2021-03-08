package dev.emg.filmhunt.data.vo

import com.google.gson.annotations.SerializedName

data class Movie(
  val id: Int,
  val adult: Boolean,
  val overview: String,
  val title: String,
  val popularity: Float,
  val video: Boolean,
  @SerializedName("original_title") val originalTitle: String,
  @SerializedName("original_language") val originalLanguage: String,
  @SerializedName("release_date") val releaseDate: String,
  @SerializedName("vote_count") val voteCount: Int,
  @SerializedName("vote_average") val voteAvg: Float,
  @SerializedName("genre_ids") val genreIds: List<Int>,
  @SerializedName("poster_path") val posterPath: String?,
  @SerializedName("backdrop_path") val backdropPath: String?
)