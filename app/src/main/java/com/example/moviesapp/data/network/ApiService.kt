package com.example.moviesapp.data.network


import com.example.moviesapp.domain.MovieNowPlayingListResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun fetchMovies(): MovieNowPlayingListResult

    companion object {
        var apiService: ApiService? = null

        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }

        private fun getClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
                .build()

    }

}