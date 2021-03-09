package dev.emg.filmhunt

import android.app.Application
import dev.emg.filmhunt.data.di.AppComponent
import dev.emg.filmhunt.data.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

open class App : Application() {

  val appComponent by lazy {
    initializeAppComponent()
  }

  open fun initializeAppComponent(): AppComponent {
    return DaggerAppComponent.factory().create(applicationContext)
  }

  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())

  }

}