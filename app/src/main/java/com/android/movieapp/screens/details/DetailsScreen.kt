package com.android.movieapp.screens.details


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.android.movieapp.R
import com.android.movieapp.model.Movie
import com.android.movieapp.model.getMovies

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {

    val newMovieList : List<Movie> = getMovies().filter { movie ->
        movie.id == movieId
    }

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {

                IconButton(onClick = {
                    navController.popBackStack()
                 }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "back arrow",
                        modifier = Modifier
                            .align(alignment = CenterVertically)
                            .padding(start = 8.dp)
                    )
                }
//                Text(text = "Movie", modifier = Modifier.padding(start = 2.dp).align(alignment = CenterVertically))
            }
        },
    ){
        Surface(color = MaterialTheme.colors.background, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
                    LazyRow{
                        items(newMovieList[0].images){ image ->
                            Card(modifier = Modifier
                                .padding(12.dp)
                                .width(354.dp)
                                .height(202.dp)) {
                                Image(
                                    painter = rememberImagePainter(data = image,
                                        builder = {
                                            crossfade(true)
                                        }),
                                    contentDescription = "Movie Poster",
                                )
                            }
                        }
                    }
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = newMovieList[0].title, modifier = Modifier
                    .padding(3.dp)
                    .align(CenterHorizontally),
                style = MaterialTheme.typography.h4)
                Text(text = "Rating : ${newMovieList[0].rating}",modifier = Modifier
                    .padding(3.dp)
                    .align(CenterHorizontally),
                    style = MaterialTheme.typography.h6)
                Text(text = "Released : ${newMovieList[0].year}",modifier = Modifier.padding(start = 32.dp, top = 3.dp, bottom = 3.dp, end = 32.dp))
                Text(text = "Genre : ${newMovieList[0].genre}",modifier = Modifier.padding(start = 32.dp, top = 3.dp, bottom = 3.dp, end = 32.dp))
                Text(text = "Directed By : ${newMovieList[0].director}",modifier = Modifier.padding(start = 32.dp, top = 3.dp, bottom = 3.dp, end = 32.dp))
                Text(text = "Starring : ${newMovieList[0].actors}",modifier = Modifier.padding(start = 32.dp, top = 3.dp, bottom = 3.dp, end = 32.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Plot : ${newMovieList[0].plot}",modifier = Modifier.padding(8.dp))
            }
        }

    }



}