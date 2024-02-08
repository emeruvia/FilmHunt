package dev.emg.filmhunt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import dev.emg.filmhunt.data.toFullImageUrl
import dev.emg.filmhunt.data.vo.Movie
import dev.emg.filmhunt.ui.MainViewModel.MainScreenUiState
import dev.emg.filmhunt.ui.MainViewModel.MainScreenUiState.Success
import dev.emg.filmhunt.ui.theme.FilmHuntTheme
import timber.log.Timber

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

          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
          ) {

            Text(
              text = "Film Hunt",
              fontWeight = FontWeight.ExtraBold,
              fontSize = 34.sp,
              textAlign = TextAlign.Center,
              modifier = Modifier
                .padding(16.dp)
            )



            when (moviesState) {
              is MainScreenUiState.Loading -> {
                Timber.d("MainScreenUiState.Loading")
              }

              is MainScreenUiState.Error -> {
                Timber.d("MainScreenUiState.Error")
              }

              is MainScreenUiState.Success -> {
                PopularMovieCarousel(movieList = (moviesState as Success).movieList)

                MovieCarouselByCategory(
                  category = "Popular",
                  movieList = (moviesState as Success).movieList
                )
                MovieCarouselByCategory(
                  category = "Trending",
                  movieList = (moviesState as Success).movieList
                )
                MovieCarouselByCategory(
                  category = "Classics",
                  movieList = (moviesState as Success).movieList
                )
                MovieCarouselByCategory(
                  category = "Something else",
                  movieList = (moviesState as Success).movieList
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun MovieCarouselByCategory(category: String, movieList: List<Movie>) {
  Column(
    modifier = Modifier
      .padding(top = 8.dp, bottom = 8.dp)
  ) {
    Text(
      text = category,
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .padding(start = 16.dp, bottom = 8.dp)
    )

    LazyRow {
      items(movieList) { item ->
        MovieCard(movie = item)
      }
    }
  }
}

@Composable
fun PopularMovieCarousel(movieList: List<Movie>) {
  LazyRow(
    modifier = Modifier
      .padding(bottom = 8.dp)
  ) {
    itemsIndexed(movieList) { index, item ->
      Box(
        modifier = Modifier
          .padding(
            start = if (index == 0) 16.dp else 8.dp,
            end = if (index == movieList.count() - 1) 16.dp else 8.dp
          )
      ) {
        PopularMovieBackDrop(movie = item)
        ElevatedCard(
          modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(8.dp)
        ) {
          Text(
            text = "$index/${movieList.count()}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
          )
        }
      }
    }
  }
}

@Composable
fun PopularMovieBackDrop(movie: Movie) {
  AsyncImage(
    model = movie.backdropPath?.toFullImageUrl(), contentDescription = null,
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .height(200.dp)
      .clip(RoundedCornerShape(24.dp))
  )
}

@Composable fun MovieCard(movie: Movie) {
  Column(
    modifier = Modifier
      .width(150.dp)
      .padding(start = 4.dp, end = 4.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    AsyncImage(
      model = movie.posterPath?.toFullImageUrl(),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .size(width = 150.dp, height = 200.dp)
        .clip(RoundedCornerShape(24.dp))
        .padding(bottom = 6.dp)
    )
    Text(
      text = movie.title,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis
    )
  }
}