package dev.emg.filmhunt.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import dev.emg.filmhunt.data.Utils
import dev.emg.filmhunt.data.vo.Movie
import dev.emg.filmhunt.databinding.ItemMovieBinding
import dev.emg.filmhunt.ui.movies.MoviesAdapter.OnMovieListener

class MovieViewHolder(private val binding: ItemMovieBinding) : ViewHolder(binding.root) {

  fun bind(movie: Movie, listener: OnMovieListener) {
    binding.moviePoster.load(Utils.getFullImageUrl(movie.posterPath))
    binding.movieDescription.text = movie.overview
    binding.movieTitle.text = movie.title
    binding.root.setOnClickListener {
      listener.onMovieClicked(movie)
    }
  }

  fun unbind() {
    // Clear image resource
    binding.moviePoster.setImageResource(0)
  }

  companion object {
    fun from(parent: ViewGroup): MovieViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = ItemMovieBinding.inflate(layoutInflater)
      return MovieViewHolder(binding)
    }
  }

}