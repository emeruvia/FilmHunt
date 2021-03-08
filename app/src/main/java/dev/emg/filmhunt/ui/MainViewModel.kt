package dev.emg.filmhunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.emg.filmhunt.data.Repository
import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Timber.e(throwable, "Error -> ${throwable.message}")
  }

  private val coroutineContext =
    Dispatchers.IO + viewModelScope.coroutineContext + coroutineExceptionHandler
  private val _queryLiveData = MutableLiveData<String>()

  val movieLiveData: LiveData<DataResult<List<Movie>>>
    get() = Transformations.switchMap(
      _queryLiveData, ::moviesFromApiByQuery
    )

  fun searchQuery(query: String) {
    _queryLiveData.postValue(query)
  }

  private fun moviesFromApiByQuery(query: String): LiveData<DataResult<List<Movie>>> {
    return repository.searchMoviesByQuery(query).asLiveData(coroutineContext)
  }

}