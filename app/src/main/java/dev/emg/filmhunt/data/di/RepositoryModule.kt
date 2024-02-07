package dev.emg.filmhunt.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.emg.filmhunt.data.repository.MovieRepository
import dev.emg.filmhunt.data.repository.Repository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun providesMovieRepository(movieRepository: MovieRepository): Repository

}