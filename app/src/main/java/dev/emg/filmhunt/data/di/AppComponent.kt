package dev.emg.filmhunt.data.di

import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dev.emg.filmhunt.data.network.MovieDbService
import dev.emg.filmhunt.data.repository.MovieRepository
import dev.emg.filmhunt.data.repository.Repository
import dev.emg.filmhunt.ui.MainActivity
import dev.emg.filmhunt.ui.moviedetail.MovieDetailFragment
import dev.emg.filmhunt.ui.movies.MoviesFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): AppComponent
  }

  fun inject(activity: MainActivity)
  fun inject(fragment: MoviesFragment)
  fun inject(fragment: MovieDetailFragment)

}