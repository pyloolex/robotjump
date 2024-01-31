package com.volganl64.robotjump

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


private const val BUTTON_HEIGHT = 0.1f
private const val BUTTON_WIDTH = 0.5f


@Composable
fun GreetingScreen(navigation: NavController)
{
    Box(Modifier.background(MENU_COLOR).fillMaxSize(),
        contentAlignment=Alignment.Center)
    {
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.weight(3.5f).background(Color.Yellow))
            Column(Modifier.weight(3f))
            {
                Text("Robot Jump")
                Button(
                { navigation.navigate("levels") },
                Modifier
                //.padding(DEFAULT_MARGIN)
                //.fillMaxHeight(BUTTON_HEIGHT)
                    .fillMaxWidth(BUTTON_WIDTH)
                )
                {
                    Text("Play")
                }

                Button(
                { navigation.navigate("rules") },
                Modifier
                //.padding(DEFAULT_MARGIN)
                // .fillMaxHeight(BUTTON_HEIGHT)
                    .fillMaxWidth(BUTTON_WIDTH)
                )
                {
                    Text("Rules")
                }
            }
            Spacer(Modifier.weight(3.5f))
        }

    }
    // Box(Modifier.background(Color.Blue).fillMaxSize()) {
    //     LazyColumn {
    //         itemsIndexed(Levels) { i, level ->
    //             Button(
    //                 { navigation.navigate("game/$i") },
    //                 Modifier.padding(DEFAULT_MARGIN).height(70.dp).width(70.dp),
    //                 shape=RoundedCornerShape(5),
    //                 colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
    //                 contentPadding=PaddingValues(0.dp),
    //             ) {
    //                 Text("${i + 1} (${arr[i]})")
    //             }
    //         }
    //     }
    // }
}


@Preview
@Composable
fun GreetingPreview()
{
    val navController = rememberNavController()
    GreetingScreen(navController)
}
