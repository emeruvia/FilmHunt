package dev.emg.filmhunt.data.network

import dev.emg.filmhunt.BuildConfig
import dev.emg.filmhunt.data.vo.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {

  @GET("search/movie?api_key=${BuildConfig.THEMOVIEDB_API_KEY}")
  suspend fun searchMovie(
    @Query("query") query: String
  ): MovieResponse

  companion object {
    const val BASE_URL = "https://api.themoviedb.org/3/"
  }
}