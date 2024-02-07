package dev.emg.filmhunt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.emg.filmhunt.data.repository.Repository
import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val _movieStateFlow = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
  val movieStateFlow: StateFlow<MainScreenUiState>
    get() = _movieStateFlow

  init {
    viewModelScope.launch(Dispatchers.IO) {
      repository.searchMoviesByQuery("Batman").collect { results ->
        when (results) {
          is DataResult.Loading -> {
            _movieStateFlow.value = MainScreenUiState.Loading
          }

          is DataResult.Error -> {
            _movieStateFlow.value = MainScreenUiState.Error
          }

          is DataResult.Success -> {
            _movieStateFlow.value = MainScreenUiState.Success(results.data)
          }
        }
      }
    }
  }

  sealed interface MainScreenUiState {
    object Loading : MainScreenUiState
    data class Success(val movieList: List<Movie>) : MainScreenUiState
    object Error : MainScreenUiState
  }
}