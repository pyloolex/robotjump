package com.volganl64.robotjump

import android.util.Log

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


private var HEADER_TEXT_SIZE = 0.sp // To be overwritten inside Composable.


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

    HEADER_TEXT_SIZE = with(LocalDensity.current) {
        HEADER_TEXT_SIZE_BASE.toSp()
    }

    val arr = IntArray(Levels.size) {0}
    var starCount = 0
    for (score in AppDatabase.instance.scoreDao().getAll())
    {
        arr[score.id] = score.stars
        starCount += score.stars
    }

    Box(Modifier.background(Color.Magenta).fillMaxSize())
    {
        Column {
            Box(Modifier.background(Color.Red).fillMaxWidth().height(50.dp),
                contentAlignment=Alignment.Center) {
                Text(
                    "Stars collected: ${starCount}/${Levels.size * 3}",
                    style=TextStyle(
                        fontSize=HEADER_TEXT_SIZE,
                        fontFamily=FONT_FAMILY,
                    ),
                )

        // val gameState = getGameState(state, properties)
        // if (gameState != GAME_STATE.IN_PROGRESS)
        // {
        //     Box(Modifier.padding(DEFAULT_MARGIN).clip(RoundedCornerShape(5.dp))
        //             .background(
        //                 if (gameState == GAME_STATE.WON) WON_COLOR else LOST_COLOR)
        //             .fillMaxSize(),
        //         contentAlignment=Alignment.Center) {
        //         Row {
        //             Text(buildMessage(gameState),
        //                  style=TextStyle(
        //                      fontSize=MOVES_TEXT_SIZE,
        //                      fontFamily=FONT_FAMILY,
        //                  )
        //             )
        //         }
        //     }
        // }
            }


            Box(Modifier.background(Color.Blue).fillMaxSize()) {
                LazyVerticalGrid(
                    GridCells.Fixed(3)
                ) {
                    itemsIndexed(Levels) { i, level ->
                        Button(
                            { navigation.navigate("game/$i") },
                            Modifier.padding(DEFAULT_MARGIN).height(70.dp).width(70.dp),
                            shape=RoundedCornerShape(5),
                            colors=ButtonDefaults.buttonColors(containerColor=Color.Cyan),
                            contentPadding=PaddingValues(0.dp),
                        ) {
                            Text("${i + 1} (${arr[i]})")
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun LevelsPreview()
{
    val navController = rememberNavController()
    LevelsScreen(navController)
}
