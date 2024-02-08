package dev.emg.filmhunt.data.repository

import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import kotlinx.coroutines.flow.Flow

interface Repository {

  fun nowPlayingMovies(): Flow<DataResult<List<Movie>>>
  fun popularMovies(): Flow<DataResult<List<Movie>>>
  fun topRatedMovies(): Flow<DataResult<List<Movie>>>
  fun upcomingMovies(): Flow<DataResult<List<Movie>>>
  fun searchMoviesByQuery(
    query: String = ""
  ): Flow<DataResult<List<Movie>>>

}