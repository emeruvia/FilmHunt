package dev.emg.filmhunt.ui.movies

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.emg.filmhunt.data.vo.Movie

class MoviesAdapter(
  private val listener: OnMovieListener
) : ListAdapter<Movie, MovieViewHolder>(DIFF_UTIL) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    return MovieViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    holder.bind(getItem(position), listener)
  }

  override fun onViewRecycled(holder: MovieViewHolder) {
    super.onViewRecycled(holder)
    holder.unbind()
  }

  interface OnMovieListener {
    fun onMovieClicked(movie: Movie)
  }

  private companion object {
    private val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
      override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
      }
    }
  }
}