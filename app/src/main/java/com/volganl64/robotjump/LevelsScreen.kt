package com.volganl64.robotjump

import android.util.Log

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


private var HEADER_TEXT_SIZE = 0.sp // To be overwritten inside Composable.
private val LOCKED_BACKGROUND_COLOR = Color(250, 250, 250, 255)


@Composable
fun drawLevels(navigation: NavController, arr : Array<Int>)
{
    HEADER_TEXT_SIZE = with(LocalDensity.current) {
        HEADER_TEXT_SIZE_BASE.toSp()
    }

    var nextLevel = 0
    var starCount = 0
    for (i in arr.indices)
    {
        starCount += arr[i]
        if (arr[i] > 0)
        {
            nextLevel = i + 1
        }
    }

    Box(Modifier.background(MENU_COLOR).fillMaxSize())
    {
        Column {
            Box(Modifier.fillMaxWidth().height(50.dp),
                contentAlignment=Alignment.Center) {
                Text(
                    "Stars collected: ${starCount}/${Levels.size * 3}",
                    style=TextStyle(
                        fontSize=HEADER_TEXT_SIZE,
                        fontFamily=FONT_FAMILY,
                    ),
                )
            }

            Box(Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    GridCells.Fixed(3),
                    Modifier.padding(10.dp),
                    verticalArrangement=Arrangement.spacedBy(10.dp),
                    horizontalArrangement=Arrangement.spacedBy(10.dp),
                ) {
                    itemsIndexed(arr) { i, score ->
                        if (i <= nextLevel)
                        {
                            Button(
                                { navigation.navigate("game/$i") },
                                Modifier//.padding(10.dp)
                                    .fillMaxHeight().aspectRatio(1f),//.padding(10.dp),
                                shape=RoundedCornerShape(5),
                                colors=ButtonDefaults.buttonColors(containerColor=Color.Cyan),
                                contentPadding=PaddingValues(0.dp),
                            ) {
                                Text("${i + 1} (${arr[i]})")
                            }
                        }
                        else
                        {
                            Button(
                                { navigation.navigate("game/$i") },
                                Modifier//.padding(10.dp)
                                    .fillMaxHeight().aspectRatio(1f),//.padding(10.dp),
                                shape=RoundedCornerShape(5),
                                colors=ButtonDefaults.buttonColors(
                                    containerColor=LOCKED_BACKGROUND_COLOR),
                                contentPadding=PaddingValues(0.dp),
                            ) {
                                Box(Modifier.fillMaxSize())
                                {
                                    Image(
                                        painter=painterResource(id=R.drawable.lock),
                                        contentDescription="locked",
                                        modifier=Modifier.align(Alignment.Center).fillMaxSize(0.8f),
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LevelsScreen(navigation: NavController)
{
    // Init for saving DB for assets.
    // AppDatabase.instance.scoreDao().deleteAll();
    // AppDatabase.instance.scoreDao().init()

    // Move data from `shm` and `wal` temporary files to the DB
    // before saving it for assets.
    // val cursor = AppDatabase.instance.query("PRAGMA wal_checkpoint", arrayOf())
    // cursor.moveToFirst()

    val arr = Array<Int>(Levels.size) {0}
    for (score in AppDatabase.instance.scoreDao().getAll())
    {
        arr[score.id] = score.stars
    }

    drawLevels(navigation, arr)
}


@Preview
@Composable
fun LevelsPreview()
{
    val navController = rememberNavController()

    val arr = arrayOf<Int>(1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    drawLevels(navController, arr)
}
