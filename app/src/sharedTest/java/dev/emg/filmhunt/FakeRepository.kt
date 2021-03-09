package dev.emg.filmhunt

import dev.emg.filmhunt.data.repository.Repository
import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class FakeRepository @Inject constructor(private val movie: Movie) : Repository {

  override fun searchMoviesByQuery(query: String) = flow {
    emit(DataResult.Success(mutableListOf(movie)))
  }

  fun searchMoviesByQueryThrowError(exception: Exception) = flow {
    emit(DataResult.Error(exception, exception.message))
  }

}