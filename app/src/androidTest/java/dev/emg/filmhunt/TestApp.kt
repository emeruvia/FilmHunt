package dev.emg.filmhunt

import dev.emg.filmhunt.di.DaggerTestAppComponent

class TestApp : App() {

  override fun initializeAppComponent(): AppComponent {
    return DaggerTestAppComponent.create()
  }

}