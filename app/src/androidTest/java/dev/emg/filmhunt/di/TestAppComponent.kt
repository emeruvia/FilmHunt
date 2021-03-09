package dev.emg.filmhunt.di

import dagger.Component
import dev.emg.filmhunt.data.di.AppComponent
import dev.emg.filmhunt.data.di.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [TestRepositoryModule::class])
interface TestAppComponent : AppComponent