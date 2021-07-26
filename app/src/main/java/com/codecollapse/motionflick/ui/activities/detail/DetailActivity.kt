package com.codecollapse.motionflick.ui.activities.detail


import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.codecollapse.motionflick.models.datamodels.MovieDetail
import com.codecollapse.motionflick.ui.theme.MotionFlickTheme
import com.codecollapse.motionflick.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.cardview_dark_background)
        setContent {
            MotionFlickTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DetailComposable()
                }
            }
        }
    }
}

@Composable
private fun DetailComposable() {

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
                    painter = painterResource(id = R.drawable.fury_detail_image),
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
                    MovieCompose()
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
                                text = "FURY",
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    fontSize = 22.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_black))
                                )
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = "(2014)",
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                                ),
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.absolutePadding(0.dp, 8.dp)
                            )

                        }

                        Text(
                            text = "Action/Drama/War",
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = colorResource(id = R.color.lightGrey),
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
                    .height(250.dp)
            ) {
                Text(
                    text = "STORYLINE",
                    textAlign = TextAlign.Start,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.black),
                        fontFamily = FontFamily(
                            Font(R.font.roboto_medium)
                        ),fontWeight = FontWeight.SemiBold
                    ),modifier = Modifier.padding(12.dp,0.dp,0.dp,0.dp)
                )
                Text(
                    text = "In April 1945, the Allies are making their final push in the European theater. A battle-hardened Army sergeant named Don \"Wardaddy\" Collier (Brad Pitt), leading a Sherman tank and a five-man crew, undertakes a deadly mission behind enemy lines. Hopelessly outnumbered, outgunned and saddled with an inexperienced soldier (Logan Lerman) in their midst, Wardaddy and his men face overwhelming odds as they move to strike at the heart of Nazi Germany.",
                    textAlign = TextAlign.Start,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.lightGrey),
                        fontFamily = FontFamily(
                            Font(R.font.roboto_regular)
                        )
                    ),modifier = Modifier.padding(12.dp),
                    fontWeight = FontWeight.W200
                )
            }
        }
    }
}

@Composable
fun MovieCompose() {
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
                painter = painterResource(id = R.drawable.fury_cover_image),
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
            color = colorResource(id = R.color.lightGrey),
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
        DetailComposable()
    }
}