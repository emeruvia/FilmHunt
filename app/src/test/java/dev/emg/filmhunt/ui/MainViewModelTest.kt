package dev.emg.filmhunt.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dev.emg.filmhunt.FakeRepository
import dev.emg.filmhunt.data.vo.DataResult
import dev.emg.filmhunt.data.vo.Movie
import dev.emg.filmhunt.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MainViewModelTest {

  @get:Rule
  var instantTaskExecutionRule = InstantTaskExecutorRule()

  @get:Rule
  val testCoroutineRule = TestCoroutineRule()

  private lateinit var repository: FakeRepository
  private lateinit var viewModel: MainViewModel

  @Mock
  private lateinit var movie: Movie
  @Mock
  private lateinit var observer: Observer<DataResult<List<Movie>>>

  @Before
  fun setup() {
    movie = mock(Movie::class.java)
    repository = FakeRepository(movie)
    viewModel = MainViewModel(repository)
    observer = mock(Observer::class.java) as Observer<DataResult<List<Movie>>>
  }

  @Test
  fun `LiveData emitting getOrAwaitValue for DataResultSuccess`() {
    val success = DataResult.Success(listOf(movie))
    viewModel.setNewValueMovieLiveData(success)
    Assert.assertEquals(viewModel.movieLiveData.getOrAwaitValue(), success)
  }

  @Test
  fun `LiveData emitting getOrAwaitValue for DataResultLoading`() {
    viewModel.setNewValueMovieLiveData(DataResult.Loading)
    Assert.assertEquals(viewModel.movieLiveData.getOrAwaitValue(), DataResult.Loading)
  }

  @Test
  fun `LiveData emitting getOrAwaitValue for DataResultError`() {
    val testException = Exception("Testing Exception")
    viewModel.setNewValueMovieLiveData(DataResult.Error(testException, testException.message))
    Assert.assertEquals(viewModel.movieLiveData.getOrAwaitValue(), DataResult.Error(testException, testException.message))
  }

  @Test
  fun `200 Response searchMovieByQuery`() {
    testCoroutineRule.runBlockingTest {
      viewModel.movieLiveData.observeForever(observer)
      repository.searchMoviesByQuery()
      verify(observer).onChanged(DataResult.Success(listOf(movie)))
      viewModel.movieLiveData.removeObserver(observer)
    }
  }

  @Test
  fun `Error Response searchMovieByQuery`() {
    testCoroutineRule.runBlockingTest {
      val testException = Exception("Forced Error")
      viewModel.movieLiveData.observeForever(observer)
      repository.searchMoviesByQueryThrowError(testException)
      verify(observer).onChanged(
        DataResult.Error(testException, testException.message)
      )
      viewModel.movieLiveData.removeObserver(observer)
    }
  }

}