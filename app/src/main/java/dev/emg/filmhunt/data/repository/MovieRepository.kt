package dev.emg.filmhunt.data.repository

import dev.emg.filmhunt.data.network.MovieDbService
import dev.emg.filmhunt.data.vo.DataResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val service: MovieDbService) : Repository {

  override fun searchMoviesByQuery(query: String) = flow {
    emit(DataResult.Success(service.searchMovie(query).results))
  }

}