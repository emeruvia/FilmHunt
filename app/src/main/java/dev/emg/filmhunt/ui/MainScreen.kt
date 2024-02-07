package dev.emg.filmhunt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import dev.emg.filmhunt.App
import dev.emg.filmhunt.data.Utils
import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import dev.emg.filmhunt.ui.MainViewModel.MainScreenUiState
import dev.emg.filmhunt.ui.MainViewModel.MainScreenUiState.Success
import dev.emg.filmhunt.ui.theme.FilmHuntTheme
import timber.log.Timber
import timber.log.Timber.Forest
import javax.inject.Inject

@AndroidEntryPoint
class MainScreen : ComponentActivity() {

  private val mainViewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {

      val moviesState by mainViewModel.movieStateFlow.collectAsState()

      FilmHuntTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

          when (moviesState) {
            is MainScreenUiState.Loading -> {
              Timber.d("MainScreenUiState.Loading")
            }

            is MainScreenUiState.Error -> {
              Timber.d("MainScreenUiState.Error")
            }

            is MainScreenUiState.Success -> {
              MovieList(movieList = (moviesState as Success).movieList)
            }
          }
        }
      }
    }
  }
}

@Composable fun MovieList(movieList: List<Movie>) {
  LazyColumn {
    items(movieList) { item ->
      MovieCard(movie = item)
    }
  }
}

@Composable fun MovieCard(movie: Movie) {
  Card(
    modifier = Modifier.size(width = 200.dp, height = 200.dp)
  ) {
    AsyncImage(model = Utils.getFullImageUrl(movie.posterPath), contentDescription = null)
    Text(text = movie.title, textAlign = TextAlign.Center)
    Text(text = movie.originalLanguage, textAlign = TextAlign.Center)
  }
}