package dev.emg.filmhunt.data.di

import dagger.Binds
import dagger.Module
import dev.emg.filmhunt.data.repository.MovieRepository
import dev.emg.filmhunt.data.repository.Repository

@Module
abstract class RepositoryModule {
  @Binds
  abstract fun providesMovieRepository(movieRepository: MovieRepository): Repository

}