package com.husqvarna.assignment.presentation.viewmodel.popular

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.domain.usecase.GetPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(private val getPopularMovies: GetPopularMovies) :
    ViewModel() {

    private val _state = mutableStateOf(MovieUiState())
    val state: State<MovieUiState> = _state

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            getPopularMovies().onEach { it ->
                when (it) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            moviesList = it.data ?: emptyList(),
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            moviesList = it.data ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            moviesList = emptyList(),
                            isLoading = false,
                            error = it.message.toString()
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}