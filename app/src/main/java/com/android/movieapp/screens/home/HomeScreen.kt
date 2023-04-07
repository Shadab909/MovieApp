package com.android.movieapp.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.movieapp.model.Movie
import com.android.movieapp.model.getMovies
import com.android.movieapp.navigation.MovieScreens
import com.android.movieapp.widgets.MovieRow


@ExperimentalAnimationApi
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.primary, elevation = 6.dp) {
                Text(text = "Movie", modifier = Modifier.padding(start = 16.dp))
            }
        },
    ) {
        MainContent(navController = navController)
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(
    navController: NavController,
    movieNameList: List<Movie> = getMovies()
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn{
            items(items = movieNameList){
                MovieRow(it){ movie->
                    navController.navigate(MovieScreens.DetailsScreen.name+"/$movie")
                }
            }
        }
    }
}
















