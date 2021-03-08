package dev.emg.filmhunt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.emg.filmhunt.App
import dev.emg.filmhunt.databinding.ActivityMainBinding
import dev.emg.filmhunt.ui.movies.MoviesFragment

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as App).appComponent.inject(this)

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(binding.container.id, MoviesFragment())
    transaction.commit()
  }
}