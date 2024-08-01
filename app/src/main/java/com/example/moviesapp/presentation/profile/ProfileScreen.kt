package com.example.moviesapp.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesapp.domain.Result
import com.example.moviesapp.presentation.signin.UserData
import com.example.moviesapp.presentation.movies.view.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    movieData: List<Result>,
    onMovieClick:(Result) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Movies App")
                }
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            if (userData != null) {
                ProfileCardRow(userData, onSignOut)
            }
            
            MovieList(movieList = movieData, onMovieClick)

        }
    }

}

@Composable
fun ProfileCardRow(userData: UserData, onSignOut: () -> Unit) {
    Row(
        modifier =
        Modifier
            .padding(4.dp)
            .wrapContentSize()

    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .weight(0.2f)
                .wrapContentSize()
        ) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .padding(4.dp)
                .weight(0.4f)
                .wrapContentSize()
        ) {
            Text(text = "Welcome")
            userData.username?.let { Text(it) }
        }

        Column(
            modifier = Modifier
                .padding(4.dp)
                .weight(0.4f)
                .wrapContentSize()
        ) {
            Button(onClick = onSignOut) {
                Text(text = "Sign out")
            }
        }
    }
}

@Composable
fun MovieList(movieList: List<Result>, onMovieClick: (Result) -> Unit) {
    LazyColumn {
        itemsIndexed(items = movieList){ index, item ->
            MovieItem(movie = item) {
                onMovieClick(item)

            }
        }
    }

}



