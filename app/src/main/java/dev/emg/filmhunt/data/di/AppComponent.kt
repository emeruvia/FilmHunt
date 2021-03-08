package dev.emg.filmhunt.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.emg.filmhunt.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): AppComponent
  }

  fun inject(activity: MainActivity)

}