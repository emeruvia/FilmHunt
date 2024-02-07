package dev.emg.filmhunt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
open class App : Application() {

  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())
  }

}