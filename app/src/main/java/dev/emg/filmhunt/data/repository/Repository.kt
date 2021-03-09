package dev.emg.filmhunt.data.repository

import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import kotlinx.coroutines.flow.Flow

interface Repository {

  fun searchMoviesByQuery(
    query: String = ""
  ): Flow<DataResult<List<Movie>>>

}