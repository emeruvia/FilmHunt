package dev.emg.filmhunt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.emg.filmhunt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)


    binding.testBtn.setOnClickListener {
      val viewmodel = MainViewModel()
      viewmodel.getMoviesFromApi()
    }
  }
}