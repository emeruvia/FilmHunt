package dev.emg.filmhunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.emg.filmhunt.data.Repository
import dev.emg.filmhunt.data.vo.Movie
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val coroutineContext = Dispatchers.IO + viewModelScope.coroutineContext
  private val _queryLiveData = MutableLiveData<String>()

  val movieLiveData: LiveData<List<Movie>> = Transformations.switchMap(
    _queryLiveData, ::moviesFromApiByQuery
  )

  fun searchQuery(query: String) {
    _queryLiveData.postValue(query)
  }

  private fun moviesFromApiByQuery(query: String): LiveData<List<Movie>> {
    return repository.searchMoviesByQuery(query).asLiveData(coroutineContext)
  }


}