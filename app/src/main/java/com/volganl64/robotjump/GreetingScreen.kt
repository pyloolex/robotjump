package com.volganl64.robotjump

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun GreetingScreen(navigation: NavController)
{
    Box(Modifier.background(Color.Blue).fillMaxSize()) {
        Column {
            Button(
            { navigation.navigate("levels") },
            Modifier.padding(DEFAULT_MARGIN).height(170.dp).width(170.dp),
            )
            {
                Text("Play")
            }

            Button(
            { navigation.navigate("rules") },
            Modifier.padding(DEFAULT_MARGIN).height(170.dp).width(170.dp),
            )
            {
                Text("Rules")
            }
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
