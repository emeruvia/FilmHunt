package dev.emg.filmhunt.di

import dagger.Binds
import dagger.Module
import dev.emg.filmhunt.FakeRepository
import dev.emg.filmhunt.data.repository.Repository

@Module
abstract class TestRepositoryModule {

  @Binds
  abstract fun provideRepository(repository: FakeRepository): Repository

}