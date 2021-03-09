package dev.emg.filmhunt.ui.movies

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import dev.emg.filmhunt.App
import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import dev.emg.filmhunt.databinding.FragmentMoviesBinding
import dev.emg.filmhunt.ui.MainViewModel
import dev.emg.filmhunt.ui.moviedetail.MovieDetailFragment
import dev.emg.filmhunt.ui.movies.MoviesAdapter.OnMovieListener
import timber.log.Timber
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
    val layoutManager =
      if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        GridLayoutManager(requireContext(), 2)
      } else {
        GridLayoutManager(requireContext(), 4)
      }
    binding.recyclerview.apply {
      this.adapter = adapter
      this.layoutManager = layoutManager
    }

    binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
          viewModel.searchApiByQuery(it)
          return true
        }
        return false
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return false
      }

    })

    viewModel.movieLiveData.observe(viewLifecycleOwner) { result ->
      when (result) {
        is DataResult.Success -> {
          adapter.submitList(result.data)
        }
        is DataResult.Loading -> {

        }
        is DataResult.Error -> {
          Timber.e(result.e, "Error -> ${result.msg}")
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onMovieClicked(movie: Movie) {
    val transaction = requireActivity().supportFragmentManager.beginTransaction()
    transaction.addToBackStack(TAG)
    transaction.replace(binding.root.id, MovieDetailFragment.create(movie), MovieDetailFragment.TAG)
    transaction.commit()
  }

  companion object {
    const val TAG = "moviesFragment"
  }
}