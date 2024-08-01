package com.example.moviesapp.presentation.movies.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.moviesapp.R
import com.example.moviesapp.domain.Result


@Composable
fun MovieItem(movie: Result, onMovieClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp)
            .clickable(onClick = onMovieClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ){
        Surface{
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ){
                Image(painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(
                        data = "https://image.tmdb.org/t/p/w185/${movie.poster_path}")
                        .apply(block = fun ImageRequest.Builder.() {
                            scale(Scale.FILL)
                            placeholder(R.drawable.ic_launcher_background)
                            transformations(CircleCropTransformation())
                        }).build()
                ),
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = movie.vote_count.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(4.dp)
                    )

                    Text(text = movie.overview,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }


            }

        }

    }

}
