package dev.emg.filmhunt.data

import dev.emg.filmhunt.data.network.MovieDbService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val service: MovieDbService) {

  fun searchMoviesByQuery(
    query: String = ""
  ) = flow {
    emit(service.searchMovie(query).results)
  }

}