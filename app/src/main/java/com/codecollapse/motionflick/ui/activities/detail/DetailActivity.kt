package com.codecollapse.motionflick.ui.activities.detail


import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.codecollapse.motionflick.R
import com.codecollapse.motionflick.commons.AppConstants
import com.codecollapse.motionflick.models.datamodels.MovieCredits
import com.codecollapse.motionflick.models.datamodels.MovieDetail
import com.codecollapse.motionflick.models.datasource.utils.Resource
import com.codecollapse.motionflick.models.viewmodel.StartUpViewModel
import com.codecollapse.motionflick.ui.theme.MotionFlickTheme
import com.google.accompanist.glide.rememberGlidePainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    private val startUpViewModel: StartUpViewModel by viewModels()
    var movieId: Int? = 0
    var movieLanguages: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.cardview_dark_background)


        if (intent.hasExtra("movieId") && intent.hasExtra("movieLanguage")) {
            movieId = intent.getIntExtra("movieId", 0)
            movieLanguages = intent.getStringExtra("movieLanguage")
        }

        setContent {
            MotionFlickTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DetailComposable(
                        movieId = movieId!!,
                        movieLanguages = movieLanguages!!,
                        startUpViewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailComposable(
    movieId: Int,
    movieLanguages: String,
    startUpViewModel: StartUpViewModel
) {

    val movieDetail: Resource<MovieDetail> by startUpViewModel.getMovieDetails(
        movieId = movieId,
        movieLanguage = movieLanguages
    ).observeAsState(initial = Resource.loading(data = null))

    val movieCredits: Resource<MovieCredits> by startUpViewModel.getMovieCredits(
        movieId = movieId,
        movieLanguage = movieLanguages
    ).observeAsState(initial = Resource.loading(data = null))

    if (movieDetail.data != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = colorResource(id = R.color.white))
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                ) {
                    Image(
                        painter = rememberGlidePainter(request = AppConstants.LOAD_BACK_DROP_BASE_URL + movieDetail.data!!.backdrop_path),
                        contentDescription = "movieCoverLogo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_back),
                            contentDescription = "backImage",
                        )
                        Image(
                            painter = painterResource(id = R.drawable.outline_favorite_24),
                            contentDescription = "favIcon",
                            alignment = Alignment.CenterEnd
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(360.dp)
                            .paddingFromBaseline(310.dp, 0.dp)
                            .absolutePadding(12.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        MovieCompose(movieDetail.data!!)
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(30.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "${movieDetail.data!!.title}",
                                    textAlign = TextAlign.Start,
                                    style = TextStyle(
                                        fontSize = 22.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_black))
                                    )
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(
                                    text = "(${movieDetail.data!!.release_date})",
                                    textAlign = TextAlign.Start,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium))
                                    ),
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.absolutePadding(0.dp, 8.dp)
                                )

                            }

                            var movieGenres = ""
                            movieDetail.data!!.genres.forEach { movieGenres = it.name!! }

                            Text(
                                text = "$movieGenres",
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = colorResource(id = R.color.black),
                                    fontFamily = FontFamily(
                                        Font(R.font.roboto_medium)
                                    )
                                )
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(30.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                var movieDataList = arrayListOf<MovieDetail.MovieSubInfo>(
                                    MovieDetail.MovieSubInfo(
                                        R.drawable.ic_visibility,
                                        "32132"
                                    ),
                                    MovieDetail.MovieSubInfo(R.drawable.ic_comment, "521"),
                                    MovieDetail.MovieSubInfo(
                                        R.drawable.ic_wall_clock,
                                        "2h 30min"
                                    )
                                )
                                LazyRow() {
                                    items(movieDataList) { list ->
                                        DataCompose(list)
                                    }
                                }
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    Text(
                        text = "STORYLINE",
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.black),
                            fontFamily = FontFamily(
                                Font(R.font.roboto_medium)
                            ), fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = "${movieDetail.data!!.overview}",
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.black),
                            fontFamily = FontFamily(
                                Font(R.font.roboto_regular)
                            )
                        ), modifier = Modifier.padding(12.dp),
                        fontWeight = FontWeight.W200
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "CAST",
                    textAlign = TextAlign.Start,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.black),
                        fontFamily = FontFamily(
                            Font(R.font.roboto_medium)
                        ), fontWeight = FontWeight.SemiBold
                    ), modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
                )
                LazyRow(
                    modifier = Modifier
                        .padding(12.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    if (movieCredits.data != null) {
                        items(movieCredits.data!!.movieCast) { movieCast ->
                            MovieCastLayout(movieCast = movieCast)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieCastLayout(movieCast: MovieCredits.MovieCast) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .height(160.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .height(120.dp)
                .fillMaxWidth()
                .clickable {
                   /* var intent = Intent(context,DetailActivity::class.java)
                    intent.putExtra("movieId",movie.id)
                    intent.putExtra("movieLanguage",movie.original_language)
                    context.startActivity(intent)*/
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 2.dp
        ) {

            Image(
                painter = rememberGlidePainter(request = AppConstants.LOAD_IMAGE_BASE_URL + movieCast.profile_path),
                contentDescription = "castProfile",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = movieCast.original_name!!,
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            modifier = Modifier.padding(4.dp)
        )
    }
}


@Composable
fun MovieCompose(movieDetail: MovieDetail) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(130.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .height(140.dp)
                .fillMaxWidth()
                .clickable {
                    //context.startActivity(Intent(context,DetailActivity::class.java))
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 2.dp
        ) {
            Image(
                painter = rememberGlidePainter(request = AppConstants.LOAD_IMAGE_BASE_URL + movieDetail.poster_path),
                contentDescription = "moviesCoverImages",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun DataCompose(list: MovieDetail.MovieSubInfo) {
    Image(
        painter = painterResource(id = list.totalViewIcon!!),
        contentDescription = "viewMovieIcon",
        modifier = Modifier
            .width(20.dp)
            .height(20.dp)
    )
    Spacer(modifier = Modifier.padding(2.dp))
    Text(
        text = list.totalViews!!,
        textAlign = TextAlign.Start,
        style = TextStyle(
            fontSize = 12.sp,
            color = colorResource(id = R.color.black),
            fontFamily = FontFamily(
                Font(R.font.roboto_medium)
            )
        )
    )
    Spacer(modifier = Modifier.padding(4.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MotionFlickTheme {
        //  DetailComposable(0,"",null!!)
    }
}