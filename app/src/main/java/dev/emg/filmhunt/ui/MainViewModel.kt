package dev.emg.filmhunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emg.filmhunt.data.repository.Repository
import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val _movieLiveData = MutableLiveData<DataResult<List<Movie>>>()
  val movieLiveData: LiveData<DataResult<List<Movie>>>
    get() = _movieLiveData

  init {
    searchApiByQuery("Batman")
  }

  fun searchApiByQuery(query: String) {
    viewModelScope.launch {
      _movieLiveData.postValue(DataResult.Loading)
      try {
        withContext(Dispatchers.IO) {
          repository.searchMoviesByQuery(query).collect {
            _movieLiveData.postValue(it)
          }
        }
      } catch (e: UnknownHostException) {
        _movieLiveData.postValue(DataResult.Error(e, e.message))
      } catch (e: HttpException) {
        _movieLiveData.postValue(DataResult.Error(e, e.message))
      } catch (e: Exception) {
        _movieLiveData.postValue(DataResult.Error(e, e.message))
      }
    }
  }

  fun setNewValueMovieLiveData(newValue: DataResult<List<Movie>>) {
    _movieLiveData.postValue(newValue)
  }
}