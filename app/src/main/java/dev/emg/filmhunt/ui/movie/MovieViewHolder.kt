package dev.emg.filmhunt.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import dev.emg.filmhunt.data.vo.Movie
import dev.emg.filmhunt.databinding.ItemMovieBinding

class MovieViewHolder(private val binding: ItemMovieBinding) : ViewHolder(binding.root) {

  fun bind(movie: Movie) {
    binding.moviePoster.load("$IMAGE_BASE_URL${movie.posterPath}")
    binding.movieDescription.text = movie.overview
    binding.movieTitle.text = movie.title
  }

  fun unbind() {

  }

  companion object {
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

    fun from(parent: ViewGroup): MovieViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = ItemMovieBinding.inflate(layoutInflater)
      return MovieViewHolder(binding)
    }
  }

}