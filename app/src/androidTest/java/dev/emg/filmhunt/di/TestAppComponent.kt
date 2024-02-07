package dev.emg.filmhunt.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestRepositoryModule::class])
interface TestAppComponent : AppComponent