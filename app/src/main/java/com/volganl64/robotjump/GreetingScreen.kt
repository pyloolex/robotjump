package com.volganl64.robotjump

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


@Composable
fun GreetingScreen(navigation: NavController)
{
    val robotJumpSize = with(LocalDensity.current) {
        (LocalConfiguration.current.screenWidthDp.dp / 15).toSp()
    }
    val buttonTextSize = with(LocalDensity.current) {
        (LocalConfiguration.current.screenHeightDp.dp / 35).toSp()
    }

    Box(Modifier.background(MENU_COLOR).fillMaxSize(),
        contentAlignment=Alignment.Center)
    {
        Column(Modifier.fillMaxWidth(0.5f))
        {
            Box(Modifier.weight(2f))
            Column(Modifier.weight(6f))
            {
                Box(Modifier.weight(20f).fillMaxWidth())
                {
                    Image(
                        painter=painterResource(R.drawable.robotimage),
                        contentDescription="robot",
                        modifier=Modifier.fillMaxSize(),
                    )
                }
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
                        .fillMaxWidth()
                        .weight(10f),
                    colors=ButtonDefaults.buttonColors(
                        containerColor=MENU_BUTTON_COLOR),
                    contentPadding=PaddingValues(0.dp),
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
                    .fillMaxWidth()
                    .weight(10f),
                    colors=ButtonDefaults.buttonColors(
                        containerColor=MENU_BUTTON_COLOR),
                    contentPadding=PaddingValues(0.dp),
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
            Box(Modifier.weight(2f))
        }

    }
}


@Preview
@Composable
fun GreetingPreview()
{
    val navController = rememberNavController()

    GreetingScreen(navController)
}
