package dev.emg.filmhunt.data

import dev.emg.filmhunt.data.network.MovieDbService
import kotlinx.coroutines.flow.flow

class Repository(private val service: MovieDbService) {

  suspend fun searchMoviesByQuery(
    query: String = ""
  ) = flow {
    emit(service.searchMovie(query))
  }

}