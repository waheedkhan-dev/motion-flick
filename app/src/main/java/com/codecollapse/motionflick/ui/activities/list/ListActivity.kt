package com.codecollapse.motionflick.ui.activities.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.ColorFilter
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
import androidx.compose.ui.viewinterop.AndroidView
import com.codecollapse.motionflick.R
import com.codecollapse.motionflick.commons.AppConstants
import com.codecollapse.motionflick.models.datamodels.MotionFlickMovies
import com.codecollapse.motionflick.models.datamodels.MovieDetail
import com.codecollapse.motionflick.models.datasource.utils.Resource
import com.codecollapse.motionflick.models.viewmodel.StartUpViewModel
import com.codecollapse.motionflick.ui.activities.detail.DetailActivity
import com.codecollapse.motionflick.ui.theme.MotionFlickTheme
import com.google.accompanist.glide.rememberGlidePainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity : ComponentActivity() {
    private val startUpViewModel: StartUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionFlickTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HeaderComposable(startUpViewModel)
                }
            }
        }
    }
}


@Composable
fun HeaderComposable(startUpViewModel: StartUpViewModel) {
    val activityContext = LocalContext.current

    val upcomingMovies: Resource<MotionFlickMovies> by startUpViewModel.getUpComingMovies()
        .observeAsState(initial = Resource.loading(data = null))

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.White)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back),
                    contentDescription = "BackArrow",
                    colorFilter = ColorFilter.tint(color = Color.Black),
                    modifier = Modifier.clickable {
                        //  activityContext.startActivity(Intent(activityContext, MainActivity::class.java))
                    }
                )
                Text(
                    text = "LIST",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 18.sp
                    ), textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_ios_share_24),
                    contentDescription = "BackArrow",
                    colorFilter = ColorFilter.tint(color = Color.Black)
                )
            }
        }
        LazyColumn() {
            if (upcomingMovies.data != null) {
                items(upcomingMovies.data!!.results) { movies->
                    MovieComposable(movies,activityContext)
                }
            }
        }
    }
}

@Composable
fun MovieComposable(upcomingMovies: MotionFlickMovies.Results,activityContext : Context) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Card(
                modifier = Modifier
                    .width(115.dp)
                    .height(165.dp)
                    .padding(8.dp) .clickable {
                        var intent = Intent(activityContext, DetailActivity::class.java)
                        intent.putExtra("movieId",upcomingMovies.id)
                        intent.putExtra("movieLanguage",upcomingMovies.original_language)
                        activityContext.startActivity(intent)
                    },
                backgroundColor = Color.White,
                shape = RoundedCornerShape(8.dp)
            ) {
                Image(
                    painter = rememberGlidePainter(request = AppConstants.LOAD_IMAGE_BASE_URL + upcomingMovies.poster_path),
                    contentDescription = "MovieImage",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.white))
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    text = upcomingMovies.original_title!!, style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.roboto_black)),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = upcomingMovies.overview!!, style = TextStyle(
                        color = colorResource(id = R.color.lightGrey),
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        fontSize = 12.sp
                    )
                )
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesListPreview() {
    MotionFlickTheme {
        //HeaderComposable()
    }
}