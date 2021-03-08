package dev.emg.filmhunt.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import dev.emg.filmhunt.App
import dev.emg.filmhunt.data.vo.Movie
import dev.emg.filmhunt.databinding.FragmentMoviesBinding
import dev.emg.filmhunt.ui.MainViewModel
import dev.emg.filmhunt.ui.moviedetail.MovieDetailFragment
import dev.emg.filmhunt.ui.movies.MoviesAdapter.OnMovieListener
import javax.inject.Inject

class MoviesFragment : Fragment(), OnMovieListener {

  private var _binding: FragmentMoviesBinding? = null
  private val binding get() = _binding!!

  @Inject
  lateinit var viewModel: MainViewModel

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (requireActivity().application as App).appComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentMoviesBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val adapter = MoviesAdapter(this)
    binding.recyclerview.apply {
      this.adapter = adapter
    }

    binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
          viewModel.searchQuery(it)
          return true
        }
        return false
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return false
      }

    })

    viewModel.movieLiveData.observe(viewLifecycleOwner) {
      adapter.submitList(it)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onMovieClicked(movie: Movie) {
    val transaction = requireActivity().supportFragmentManager.beginTransaction()
    transaction.addToBackStack(TAG)
    transaction.replace(binding.root.id, MovieDetailFragment.create(movie))
    transaction.commit()
  }

  companion object {
    const val TAG = "moviesFragment"
  }
}