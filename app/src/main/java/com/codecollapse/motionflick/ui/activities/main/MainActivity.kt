package com.codecollapse.motionflick.ui.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.codecollapse.motionflick.commons.AppConstants
import com.codecollapse.motionflick.models.datamodels.CoverPhoto
import com.codecollapse.motionflick.models.datamodels.FilterItems
import com.codecollapse.motionflick.models.datamodels.MotionFlickMovies
import com.codecollapse.motionflick.models.datasource.utils.Resource
import com.codecollapse.motionflick.models.viewmodel.StartUpViewModel
import com.codecollapse.motionflick.ui.activities.detail.DetailActivity
import com.codecollapse.motionflick.ui.activities.list.ListActivity
import com.codecollapse.motionflick.ui.theme.MotionFlickTheme
import com.google.accompanist.glide.rememberGlidePainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val startUpViewModel: StartUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        setContent {
            MotionFlickTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomePage(startUpViewModel = startUpViewModel)
                }
            }
        }
    }
}


@Composable
private fun HomePage(startUpViewModel: StartUpViewModel) {
    val context = LocalContext.current
    val trendingMoviesList: Resource<MotionFlickMovies> by startUpViewModel.getTrendingMoviesList()
        .observeAsState(
            initial = Resource.loading(data = null)
        )
    val topRatedMovies: Resource<MotionFlickMovies> by startUpViewModel.getTopRatedMovies()
        .observeAsState(
            initial = Resource.loading(data = null)
        )

    Log.d("LoadingData", "HomePage: $trendingMoviesList")
    val movieCoverPhotosList = arrayListOf(
        CoverPhoto(R.drawable.lone_serviver),
        CoverPhoto(R.drawable.world_war_z_cover), CoverPhoto
            (R.drawable.avengers_cover_photo)
    )

    val filterItemsList = arrayListOf(
        FilterItems(R.drawable.ic_baseline_local_fire_department_24, "Hot"),
        FilterItems(R.drawable.ic_baseline_playlist_play_24, "Notice"),
        FilterItems(R.drawable.ic_baseline_list_24, "List"),
        FilterItems(R.drawable.ic_baseline_sort_24, "Sort")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.white))
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
                LazyRow {
                    items(filterItemsList) { filterItem ->
                        FilterItemsCard(filterItem, context)
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
                if (trendingMoviesList.data != null) {
                    items(trendingMoviesList.data!!.results) { movie ->
                        LatestMoviesLayout(movie = movie, context = context)
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
                    text = "Top Rated", textAlign = TextAlign.Justify, style = TextStyle(
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
                if (topRatedMovies.data != null) {
                    items(topRatedMovies.data!!.results) { movie ->
                        LatestMoviesLayout(movie = movie, context = context)
                    }
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
private fun FilterItemsCard(filterItems: FilterItems, context: Context) {

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(24.dp, 0.dp, 24.dp, 0.dp)
            .clickable {
                when (filterItems.itemName) {
                    "List" -> {
                        context.startActivity(Intent(context, ListActivity::class.java))
                    }
                }
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
private fun LatestMoviesLayout(movie: MotionFlickMovies.Results, context: Context) {
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
                .height(150.dp)
                .fillMaxWidth()
                .clickable {
                    var intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("movieId", movie.id)
                    intent.putExtra("movieLanguage", movie.original_language)
                    context.startActivity(intent)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 2.dp
        ) {

            Image(
                painter = rememberGlidePainter(request = AppConstants.LOAD_IMAGE_BASE_URL + movie.poster_path),
                contentDescription = "moviesCoverImages",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = movie.title!!,
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
        HomePage(startUpViewModel = null!!)
    }
}