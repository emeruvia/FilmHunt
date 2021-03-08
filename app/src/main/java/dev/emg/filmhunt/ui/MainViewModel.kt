package dev.emg.filmhunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emg.filmhunt.data.Repository
import dev.emg.filmhunt.data.vo.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val _movieLiveData = MutableLiveData<List<Movie>>()
  val movieLiveData: LiveData<List<Movie>> get() = _movieLiveData

  init {
    getMoviesFromApi()
  }

  fun getMoviesFromApi() {
    viewModelScope.launch {
      try {
        withContext(Dispatchers.IO) {
          val response = repository.searchMoviesByQuery("batman")
          response.collect {
            _movieLiveData.postValue(it.results)
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