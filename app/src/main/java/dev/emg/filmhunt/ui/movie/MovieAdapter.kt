package dev.emg.filmhunt.ui.movie

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.emg.filmhunt.data.vo.Movie

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(DIFF_UTIL) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    return MovieViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  override fun onViewRecycled(holder: MovieViewHolder) {
    super.onViewRecycled(holder)
    holder.unbind()
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