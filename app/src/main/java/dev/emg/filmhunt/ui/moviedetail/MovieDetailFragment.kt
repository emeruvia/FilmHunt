package dev.emg.filmhunt.ui.moviedetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import dev.emg.filmhunt.App
import dev.emg.filmhunt.data.Utils
import dev.emg.filmhunt.data.vo.Movie
import dev.emg.filmhunt.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

  private var _binding: FragmentMovieDetailBinding? = null
  private val binding get() = _binding!!

  private var movie: Movie? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (requireActivity().application as App).appComponent.inject(this)
  }


  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

    movie = arguments?.get(ARGS_MOVIE) as Movie

    return binding.root
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    movie?.let { movie ->
      binding.overview.text = movie.overview
      binding.language.text = movie.originalLanguage
      binding.voteAverage.text = movie.voteAvg.toString()
      movie.backdropPath?.let {
        binding.poster.load(Utils.getFullImageUrl(it))
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  companion object {
    private const val ARGS_MOVIE = "args_movie"

    const val TAG = "movieDetailFragment"

    fun create(movie: Movie): MovieDetailFragment {
      val fragment = MovieDetailFragment()

      val bundle = Bundle()
      bundle.putParcelable(ARGS_MOVIE, movie)
      fragment.arguments = bundle
      return fragment
    }
  }
}