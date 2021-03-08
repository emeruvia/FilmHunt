package dev.emg.filmhunt.data

import dev.emg.filmhunt.data.network.MovieDbService
import dev.emg.filmhunt.data.vo.DataResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val service: MovieDbService) {

  fun searchMoviesByQuery(
    query: String = ""
  ) = flow {
    emit(DataResult.Loading)
    emit(DataResult.Success(service.searchMovie(query).results))
  }

}