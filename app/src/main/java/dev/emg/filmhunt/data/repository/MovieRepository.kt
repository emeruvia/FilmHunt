package dev.emg.filmhunt.data.repository

import dev.emg.filmhunt.data.network.MovieDbService
import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
  private val service: MovieDbService
) : Repository {

  override fun nowPlayingMovies()= flow {
    val results = service.nowPlayingMovies().results
    emit(DataResult.Success(results) as DataResult<List<Movie>>)
  }.catch { throwable ->
    emit(DataResult.Error(throwable, throwable.message))
  }.onStart {
    emit(DataResult.Loading)
  }

  override fun popularMovies()= flow {
    val results = service.popularMovies().results
    emit(DataResult.Success(results) as DataResult<List<Movie>>)
  }.catch { throwable ->
    emit(DataResult.Error(throwable, throwable.message))
  }.onStart {
    emit(DataResult.Loading)
  }

  override fun topRatedMovies()= flow {
    val results = service.topRatedMovies().results
    emit(DataResult.Success(results) as DataResult<List<Movie>>)
  }.catch { throwable ->
    emit(DataResult.Error(throwable, throwable.message))
  }.onStart {
    emit(DataResult.Loading)
  }

  override fun upcomingMovies()= flow {
    val results = service.upcomingMovies().results
    emit(DataResult.Success(results) as DataResult<List<Movie>>)
  }.catch { throwable ->
    emit(DataResult.Error(throwable, throwable.message))
  }.onStart {
    emit(DataResult.Loading)
  }

  override fun searchMoviesByQuery(query: String) = flow {
    val results = service.searchMovie(query).results
    emit(DataResult.Success(results) as DataResult<List<Movie>>)
  }.catch { throwable ->
    emit(DataResult.Error(throwable, throwable.message))
  }.onStart {
    emit(DataResult.Loading)
  }

}