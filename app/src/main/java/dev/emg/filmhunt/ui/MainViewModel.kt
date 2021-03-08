package dev.emg.filmhunt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emg.filmhunt.data.Repository
import dev.emg.filmhunt.data.network.MovieDbService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel() : ViewModel() {

  fun getMoviesFromApi() {
    viewModelScope.launch {
      try {
        withContext(Dispatchers.IO) {
          val logging = HttpLoggingInterceptor()
          logging.level = Level.BASIC

          val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

          val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MovieDbService.BASE_URL)
            .build()
          val repository = Repository(service = retrofit.create(MovieDbService::class.java))
          val response = repository.searchMoviesByQuery("batman")
          response.collect {
            it.results.forEach { movie ->
              Log.d("MainViewModel", "Response -> ${movie.title}")
            }
          }
        }
      } catch (e: Exception) {
        Log.e("MainViewModel", "Exception -> ${e}")
      }
    }
  }

}