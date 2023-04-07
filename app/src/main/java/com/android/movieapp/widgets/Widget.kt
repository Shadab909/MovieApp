package com.android.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.android.movieapp.R
import com.android.movieapp.model.Movie
import com.android.movieapp.model.getMovies

@ExperimentalAnimationApi
@Preview
@Composable
fun MovieRow(movie : Movie = getMovies()[0], onItemClick : (String) -> Unit = {} ) {

    var expanded by rememberSaveable{ mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
//            .height(120.dp)
//            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation = 4.dp
    ) {

        Row(horizontalArrangement = Arrangement.Start) {

                Surface(modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
//                .clip(shape = RoundedCornerShape(corner = CornerSize(8.dp))),
                    elevation = 2.dp,
                    shape = CircleShape,
                    border = BorderStroke(width = 2.dp, color = Color.Gray)
                ) {
                    Image(
                        painter = rememberImagePainter(data = movie.images[0],
                            builder = {
                                crossfade(true)
                                transformations(CircleCropTransformation())
                            }),
                        contentDescription = "Movie Poster",
                    )
                }

                Column(modifier = Modifier
                    .weight(1f)
                    .padding(top = 12.dp, bottom = 12.dp, start = 4.dp, end = 12.dp)
                ) {
                    Text(text = movie.title, style = MaterialTheme.typography.h6)
                    Text(text = "Director : ${movie.director}" , style = MaterialTheme.typography.caption)
                    Text(text = "Released : ${movie.year}" , style = MaterialTheme.typography.caption)

                    AnimatedVisibility(visible = expanded ) {
                        Column() {
                            Text(text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                    append("Plot : ")
                                }
                                withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp , fontWeight = FontWeight.Light)){
                                    append(movie.plot)
                                }
                            })
                        }
                    }
                }

                IconButton(onClick = {
                        expanded = !expanded
                }) {
                    if (expanded){
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_up),
                            contentDescription = "back arrow",
                        )
                    }else{
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_down),
                            contentDescription = "back arrow",
                        )
                    }



                }



        }
    }
}
















