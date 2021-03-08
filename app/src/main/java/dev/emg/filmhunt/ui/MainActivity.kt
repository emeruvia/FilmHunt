package dev.emg.filmhunt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import dev.emg.filmhunt.App
import dev.emg.filmhunt.databinding.ActivityMainBinding
import dev.emg.filmhunt.ui.movie.MovieAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  @Inject
  lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as App).appComponent.inject(this)

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val adapter = MovieAdapter()
    binding.recyclerview.apply {
      this.adapter = adapter
    }

    viewModel.movieLiveData.observe(this) {
      adapter.submitList(it)
    }
  }
}