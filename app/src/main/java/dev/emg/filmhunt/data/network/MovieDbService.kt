package dev.emg.filmhunt.data.network

import dev.emg.filmhunt.BuildConfig
import dev.emg.filmhunt.data.vo.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {

  @GET("search/movie")
  suspend fun searchMovie(
    @Query("query") query: String
  ): MovieResponse

  @GET("movie/now_playing")
  suspend fun nowPlayingMovies(
    @Query("page") page: Int = 10
  ): MovieResponse

  @GET("movie/popular")
  suspend fun popularMovies(
    @Query("page") page: Int = 10
  ): MovieResponse

  @GET("movie/top_rated")
  suspend fun topRatedMovies(
    @Query("page") page: Int = 10
  ): MovieResponse

  @GET("movie/upcoming")
  suspend fun upcomingMovies(
    @Query("page") page: Int = 10
  ): MovieResponse

  companion object {
    const val BASE_URL = "https://api.themoviedb.org/3/"
  }
}