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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


private const val BUTTON_HEIGHT = 0.1f
private val BUTTON_TEXT_SIZE = 20.sp
private const val BUTTON_WIDTH = 0.5f


@Composable
fun GreetingScreen(navigation: NavController)
{
    val robotJumpSize = with(LocalDensity.current) {
        (LocalConfiguration.current.screenWidthDp.dp / 15).toSp()
    }
    val buttonTextSize = with(LocalDensity.current) {
        (LocalConfiguration.current.screenHeightDp.dp / 30).toSp()
    }

    Box(Modifier.background(MENU_COLOR).fillMaxSize(),
        contentAlignment=Alignment.Center)
    {
        Column(Modifier.fillMaxWidth(BUTTON_WIDTH)) {
            Box(Modifier.weight(3f))
            Column(Modifier.weight(6f))
            {
                Box(Modifier.weight(20f).fillMaxWidth(),
                    contentAlignment=Alignment.Center)
                {
                    Text(
                        "Robot Jump",
                        style=TextStyle(
                            fontSize=robotJumpSize,
                            fontFamily=FONT_FAMILY,
                        )
                    )
                }

                Button(
                { navigation.navigate("levels") },
                Modifier
                    .padding(DEFAULT_MARGIN)
                    .fillMaxWidth().weight(10f)
                )
                {
                    Text(
                        "Play",
                        style=TextStyle(
                            fontSize=buttonTextSize,
                            fontFamily=FONT_FAMILY,
                        ),
                    )
                }

                Button(
                { navigation.navigate("rules") },
                Modifier
                    .padding(DEFAULT_MARGIN)
                    .fillMaxWidth().weight(10f)
                )
                {
                    Text(
                        "Rules",
                        style=TextStyle(
                            fontSize=buttonTextSize,
                            fontFamily=FONT_FAMILY,
                        ),
                    )
                }
            }
            Box(Modifier.weight(3f))
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
