package com.codecollapse.motionflick.ui.activities.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.codecollapse.motionflick.ui.theme.MotionFlickTheme

class ListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionFlickTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HeaderComposable()
                }
            }
        }
    }
}

@Preview
@Composable
fun HeaderComposable() {
    val activityContext = LocalContext.current
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
        LazyColumn(){
            items(5){
                MovieComposable()
            }
        }
    }

}


@Preview
@Composable
fun MovieComposable() {
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
                    .padding(8.dp),
                backgroundColor = Color.White,
                shape = RoundedCornerShape(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fury_cover_image),
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
                    text = "Fury", style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.roboto_black)),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(0.dp,16.dp,0.dp,0.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "Brad Pitt,Shia LaBeouf,Longan Lerman", style = TextStyle(
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