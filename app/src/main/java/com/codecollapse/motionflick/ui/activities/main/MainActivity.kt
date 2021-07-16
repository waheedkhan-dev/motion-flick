package com.codecollapse.motionflick.ui.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.codecollapse.motionflick.R
import com.codecollapse.motionflick.models.datamodels.CoverPhoto
import com.codecollapse.motionflick.models.datamodels.FilterItems
import com.codecollapse.motionflick.models.datamodels.Movies
import com.codecollapse.motionflick.ui.activities.detail.DetailActivity
import com.codecollapse.motionflick.ui.theme.MotionFlickTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        setContent {
            MotionFlickTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomePage()
                }
            }
        }
    }
}


@Composable
private fun HomePage() {
    val context = LocalContext.current
    val movieCoverPhotosList = arrayListOf<CoverPhoto>(
        CoverPhoto(R.drawable.lone_serviver),
        CoverPhoto(R.drawable.world_war_z_cover), CoverPhoto
            (R.drawable.avengers_cover_photo)
    )

    val filterItemsList = arrayListOf<FilterItems>(
        FilterItems(R.drawable.ic_baseline_local_fire_department_24, "Hot"),
        FilterItems(R.drawable.ic_baseline_playlist_play_24, "Notice"),
        FilterItems(R.drawable.ic_baseline_list_24, "List"),
        FilterItems(R.drawable.ic_baseline_sort_24, "Sort")
    )

    val moviesList = arrayListOf<Movies>(
        Movies("Fury", R.drawable.fury_cover_image),
        Movies("The Hunter", R.drawable.the_hunter_cover_image),
        Movies("DeepWaterHorizon", R.drawable.deep_water_horizon_cover_image),
        Movies("MidWay", R.drawable.mid_way_cover_image),
        Movies("John Wick 3", R.drawable.john_wick_cover_image),
        Movies("Shooter", R.drawable.shooter_cover_image)
    )

    val isOnAirMoviesList = arrayListOf<Movies>(
        Movies("Cold PurSuit", R.drawable.cold_pursuit_cover_image),
        Movies("Alpha-4", R.drawable.alpha_4_cover_image),
        Movies("The Equalizer-II", R.drawable.the_equalizer_2_cover_image),
        Movies("Epic", R.drawable.epic_cover_image),
        Movies("Bird Box", R.drawable.bird_box_cover_image),
        Movies("Mine", R.drawable.mine_cover_image)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_icon_red),
                    contentDescription = "movieLogo",
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "MOTION FLICK",
                    style = TextStyle(
                        color = colorResource(id = R.color.primaryRed),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_black))
                    )
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            LazyRow(
                modifier = Modifier
                    .padding(12.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                items(movieCoverPhotosList) { item ->
                    HeaderCards(item = item)
                }
            }
            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                LazyRow() {
                    items(filterItemsList) { filterItem ->
                        FilterItemsCard(filterItem)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Divider(
                    color = colorResource(id = R.color.secondaryBlue), modifier = Modifier
                        .height(12.dp)
                        .width(3.dp)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Trending Now", textAlign = TextAlign.Justify, style = TextStyle(
                        color = colorResource(
                            id = R.color.black
                        ),
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium))
                    )
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            LazyRow(
                modifier = Modifier
                    .padding(12.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                items(moviesList) { movie ->
                    LatestMoviesLayout(movie = movie,context = context)
                }
            }

            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Divider(
                    color = colorResource(id = R.color.secondaryBlue), modifier = Modifier
                        .height(12.dp)
                        .width(3.dp)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Latest Online", textAlign = TextAlign.Justify, style = TextStyle(
                        color = colorResource(
                            id = R.color.black
                        ),
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium))
                    )
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            LazyRow(
                modifier = Modifier
                    .padding(12.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                items(isOnAirMoviesList) { movie ->
                    LatestMoviesLayout(movie = movie,context = context)
                }
            }
        }


    }
}

@Composable
private fun HeaderCards(item: CoverPhoto) {

    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(4.dp)
            .clickable {

            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = item.movieCoverPhoto!!),
            contentDescription = "moviesCoverPhotos"
        )
    }
}

@Composable
private fun FilterItemsCard(filterItems: FilterItems) {

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(24.dp, 0.dp, 24.dp, 0.dp)
            .clickable {

            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = filterItems.itemImage!!),
            contentDescription = "filterImages",
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = filterItems.itemName!!, textAlign = TextAlign.Center, style = TextStyle(
                color = colorResource(
                    id = R.color.black
                ), fontFamily = FontFamily(Font(R.font.roboto_medium))
            )
        )
    }
    Spacer(modifier = Modifier.padding(2.dp))
}

@Composable
private fun LatestMoviesLayout(movie: Movies,context : Context) {
    Column(
        modifier = Modifier
            .width(110.dp)
            .height(180.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .height(140.dp)
                .fillMaxWidth()
                .clickable {
                    context.startActivity(Intent(context, DetailActivity::class.java))
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 2.dp
        ) {
            Image(
                painter = painterResource(id = movie.movieCoverPhoto!!),
                contentDescription = "moviesCoverImages",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = movie.movieName!!,
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MotionFlickTheme {
        HomePage()
    }
}