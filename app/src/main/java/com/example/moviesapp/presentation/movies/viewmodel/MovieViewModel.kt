package com.example.moviesapp.presentation.movies.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.Result
import com.example.moviesapp.data.network.ApiService
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    var movieListResponse:List<Result> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    init {
        getMovieList()
    }

    private fun getMovieList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try{
                val movieList = apiService.fetchMovies().results
                movieListResponse = movieList
            }catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}