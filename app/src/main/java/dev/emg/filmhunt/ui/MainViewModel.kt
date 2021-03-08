package dev.emg.filmhunt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emg.filmhunt.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  fun getMoviesFromApi() {
    viewModelScope.launch {
      try {
        withContext(Dispatchers.IO) {
          val response = repository.searchMoviesByQuery("batman")
          response.collect {
            it.results.forEach { movie ->
              Timber.d("Response -> ${movie.title}")
            }
          }
        }
      } catch (e: Exception) {
        Timber.e(e, "Exception -> $e")
      }
    }
  }

}