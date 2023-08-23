package com.volganl64.robotjump

import android.os.Bundle
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import com.volganl64.robotjump.ui.theme.RobotJumpTheme


val MENU_WIDTH = 80.dp
val DEFAULT_MARGIN = 8.dp
val STAR_SIZE = 20.dp
val FONT_FAMILY = FontFamily.SansSerif
var MOVES_TEXT_SIZE = 100.sp // To be overwritten inside Composable.


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DrawRobotLayout()
        }
    }
}


@Composable
fun ColumnScope.Header()
{
    Box(Modifier.background(Color.Red).fillMaxWidth().height(50.dp)) {
        Box(Modifier.padding(DEFAULT_MARGIN).clip(RoundedCornerShape(5.dp))
                .background(Color.White)
                .fillMaxSize(),
            contentAlignment=Alignment.Center) {
            Row {
                Text("You lost: beyond the target.",
                     style=TextStyle(
                         fontSize=MOVES_TEXT_SIZE,
                         fontFamily=FONT_FAMILY,
                     )
                )
            }
        }
    }
}


@Composable
fun RowScope.LeftBar()
{
    Box(Modifier.background(Color.Green).fillMaxHeight()
            .width(MENU_WIDTH)) {
        Column {
            Box(Modifier.padding(DEFAULT_MARGIN).background(Color.White)
                    .fillMaxWidth().height(30.dp)) {
                Row(Modifier.align(Alignment.Center)) {
                    Box(Modifier.fillMaxHeight()) {
                        Image(painter=painterResource(id=R.drawable.star),
                              contentDescription="star",
                              modifier=Modifier.align(Alignment.Center).size(STAR_SIZE),
                        )
                    }
                    Box(Modifier.fillMaxHeight()) {
                        Image(painter=painterResource(id=R.drawable.star),
                              contentDescription="star",
                              modifier=Modifier.align(Alignment.Center).size(STAR_SIZE),
                        )
                    }
                    Box(Modifier.fillMaxHeight()) {
                        Image(painter=painterResource(id=R.drawable.star),
                              contentDescription="star",
                              modifier=Modifier.align(Alignment.Center).size(STAR_SIZE),
                        )
                    }
                }
            }
            Box(Modifier.padding(start=DEFAULT_MARGIN, end=DEFAULT_MARGIN)
                    .background(Color.Red)
                    .fillMaxWidth().height(30.dp)) {
                Text("256/340",
                     modifier=Modifier.align(Alignment.Center),
                     style=TextStyle(
                         fontSize=MOVES_TEXT_SIZE,
                         fontFamily=FONT_FAMILY,
                     ))
            }
            Spacer(Modifier.weight(1f))
            Button(
                {},
                Modifier.padding(start=DEFAULT_MARGIN, end=DEFAULT_MARGIN)
                    .fillMaxWidth().aspectRatio(1f),
                shape=RoundedCornerShape(5.dp),
                colors=ButtonDefaults.buttonColors(containerColor=Color.Yellow),
                contentPadding=PaddingValues(0.dp),
            ) {
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Image(painter=painterResource(id=R.drawable.up),
                              contentDescription="up",
                              modifier=Modifier.align(Alignment.Center).size(15.dp),
                        )
                    }
                    Box(Modifier.fillMaxWidth()) {
                        Text(
                            "15",
                            Modifier.align(Alignment.Center),
                            style=TextStyle(
                                fontSize=MOVES_TEXT_SIZE,
                                fontFamily=FONT_FAMILY,
                                color=Color.Black,
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun RowScope.Screen()
{
    val textSize = with(LocalDensity.current) {
        ((LocalConfiguration.current.screenWidthDp.dp - MENU_WIDTH) /
             15).toSp()
    }

    Box(Modifier.clip(RoundedCornerShape(5.dp))
            .background(Color.Blue).fillMaxHeight()
            .weight(1f)) {
        Column {
            Row(Modifier.fillMaxSize().weight(1f)) {
                Box(Modifier.background(Color.Black)
                        .fillMaxSize().weight(1f))
                Box(Modifier.background(Color.Yellow)
                        .fillMaxSize().weight(1f)) {
                    Column(Modifier.align(Alignment.Center)) {
                        Box(Modifier.fillMaxWidth()) {
                            Text("Target",
                                 modifier=Modifier.align(Alignment.Center),
                                 style=TextStyle(
                                     fontSize=textSize,
                                     fontFamily=FONT_FAMILY,
                                 ),
                            )
                        }
                        Box(Modifier.fillMaxWidth()) {
                            Text("(x=114; y=578)",
                                 modifier=Modifier.align(Alignment.Center),
                                 style=TextStyle(
                                     fontSize=textSize,
                                     fontFamily=FONT_FAMILY,
                                 ),
                            )
                        }
                    }
                }
            }
            Row(Modifier.weight(1f)) {
                Box(Modifier.background(Color.White)
                        .fillMaxSize().weight(1f)) {
                    Column(Modifier.align(Alignment.Center)) {
                        Box(Modifier.fillMaxWidth()) {
                            Text("Position",
                                 modifier=Modifier.align(Alignment.Center),
                                 style=TextStyle(
                                     fontSize=textSize,
                                     fontFamily=FONT_FAMILY,
                                 ),
                            )
                        }
                        Box(Modifier.fillMaxWidth()) {
                            Text("(x=34; y=57)",
                                 modifier=Modifier.align(Alignment.Center),
                                 style=TextStyle(
                                     fontSize=textSize,
                                     fontFamily=FONT_FAMILY,
                                 ),
                            )
                        }
                    }
                }
                Box(Modifier.background(Color.Black)
                        .fillMaxSize().weight(1f))
            }
        }
    }
}


@Composable
fun ColumnScope.Body()
{
    Box(Modifier.background(Color.Magenta).weight(1f)
            .fillMaxWidth()) {
        Row {
            LeftBar()
            Screen()
        }
    }

}


@Composable
fun ColumnScope.Footer()
{
    Box(Modifier.background(Color.LightGray).height(MENU_WIDTH)
            .fillMaxWidth()) {
        Row {
            Button(
                {},
                Modifier.padding(DEFAULT_MARGIN)
                    .fillMaxHeight().aspectRatio(1f),
                shape=RoundedCornerShape(5),
                colors=ButtonDefaults.buttonColors(containerColor=Color.Yellow),
                contentPadding=PaddingValues(0.dp),
            ) {
                Row {
                    Box(Modifier.fillMaxHeight()) {
                        Image(painter=painterResource(id=R.drawable.upgrade),
                              contentDescription="upgrade",
                              modifier=Modifier.align(Alignment.Center).size(17.dp),
                        )
                    }
                    Box(Modifier.fillMaxHeight()) {
                        Text(
                            "+1",
                            Modifier.align(Alignment.Center),
                            style=TextStyle(
                                fontSize=MOVES_TEXT_SIZE,
                                fontFamily=FONT_FAMILY,
                                color=Color.Black,
                            )
                        )
                    }
                }
            }
            Button(
                {},
                Modifier.padding(top=DEFAULT_MARGIN, bottom=DEFAULT_MARGIN)
                    .fillMaxHeight().aspectRatio(1f),
                shape=RoundedCornerShape(5),
                colors=ButtonDefaults.buttonColors(containerColor=Color.Yellow),
                contentPadding=PaddingValues(0.dp),
            ) {
                Row {
                    Box(Modifier.fillMaxHeight()) {
                        Text(
                            "15",
                            Modifier.align(Alignment.Center),
                            style=TextStyle(
                                fontSize=MOVES_TEXT_SIZE,
                                fontFamily=FONT_FAMILY,
                                color=Color.Black,
                            )
                        )
                    }
                    Spacer(Modifier.width(4.dp))
                    Box(Modifier.fillMaxHeight()) {
                        Image(painter=painterResource(id=R.drawable.right),
                              contentDescription="upgrade",
                              modifier=Modifier.align(Alignment.Center).size(15.dp),
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                {},
                Modifier.padding(top=DEFAULT_MARGIN, bottom=DEFAULT_MARGIN)
                    .fillMaxHeight().aspectRatio(1f),
                shape=RoundedCornerShape(5),
                colors=ButtonDefaults.buttonColors(containerColor=Color.Yellow),
                contentPadding=PaddingValues(0.dp),
            ) {
                Box(Modifier.fillMaxHeight()) {
                    Image(painter=painterResource(id=R.drawable.undo),
                          contentDescription="upgrade",
                          modifier=Modifier.align(Alignment.Center).size(35.dp),
                    )
                }
            }
            Button(
                {},
                Modifier.padding(DEFAULT_MARGIN)
                    .fillMaxHeight().aspectRatio(1f),
                shape=RoundedCornerShape(5),
                colors=ButtonDefaults.buttonColors(containerColor=Color.Yellow),
                contentPadding=PaddingValues(0.dp),
            ) {
                Box(Modifier.fillMaxHeight()) {
                    Image(painter=painterResource(id=R.drawable.restart),
                          contentDescription="upgrade",
                          modifier=Modifier.align(Alignment.Center).size(35.dp),
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DrawRobotLayout()
{
    MOVES_TEXT_SIZE = with(LocalDensity.current) {
        16.dp.toSp()
    }

    Box(Modifier.background(Color.Cyan).fillMaxSize()/*.width(300.dp).height(600.dp)*/) {
        Column {
            Header()
            Body()
            Footer()
        }
    }
}
