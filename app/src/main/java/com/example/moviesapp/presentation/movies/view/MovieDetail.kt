package com.example.moviesapp.presentation.movies.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.moviesapp.R
import com.example.moviesapp.domain.Result
import com.example.moviesapp.utils.MainAppBar

@Composable
fun MovieDetailScreen(movie: Result, navController: NavHostController){
    Scaffold(
        topBar = { MainAppBar(navController, R.string.details) }
    ) { padding ->
        MovieDetail(
            modifier = Modifier.padding(padding),
            movie
        )
    }

}

@Composable
fun MovieDetail(modifier: Modifier, movie: Result) {
    Column(
        modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Image(painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(
                    data = "https://image.tmdb.org/t/p/w185/${movie.poster_path}")
                    .apply(block = fun ImageRequest.Builder.() {
                        scale(Scale.FILL)
                        placeholder(R.drawable.ic_launcher_background)
                    })
                    .build()

            ),
                contentDescription = movie.title,
                modifier = Modifier.wrapContentSize(align = Alignment.Center)
            )

        Text(text = movie.title, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Text(text = movie.release_date, fontSize = 12.sp)
        Text(text = movie.original_language, fontSize = 12.sp)
        Text(text = movie.overview, fontSize = 18.sp)
    }

}
