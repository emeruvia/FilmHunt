package dev.emg.filmhunt.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.emg.filmhunt.BuildConfig
import dev.emg.filmhunt.data.network.MovieDbService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Singleton
  @Provides
  fun provideMovieDbService(okHttpClient: OkHttpClient): MovieDbService {
    return Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(MovieDbService.BASE_URL)
      .client(okHttpClient)
      .build()
      .create(MovieDbService::class.java)
  }

  @Singleton
  @Provides
  fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(interceptor)
      .build()
  }

  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
      loggingInterceptor.level = BASIC
    } else {
      loggingInterceptor.level = NONE
    }
    return loggingInterceptor
  }

}