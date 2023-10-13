package com.husqvarna.assignment.presentation.viewmodel.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.domain.usecase.GetMovieDetails
import com.husqvarna.assignment.presentation.viewmodel.popular.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetails: GetMovieDetails) : ViewModel() {
    private val _state = mutableStateOf(MovieDetailUiState())
    val state: State<MovieDetailUiState> = _state

    fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
            getMovieDetails(movieId)
                .onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.value = MovieDetailUiState(isLoading = true)
                        }
                        is Resource.Success -> {
                            _state.value = MovieDetailUiState(movieDetail = result.data)
                        }
                        is Resource.Error -> {
                            _state.value = MovieDetailUiState(error = result.message!!)
                        }
                    }
                }
                .launchIn(this)
        }
    }
}
