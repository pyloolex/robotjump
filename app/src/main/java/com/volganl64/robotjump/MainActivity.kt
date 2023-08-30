package com.volganl64.robotjump

import kotlin.math.max

import android.os.Bundle
import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.saveable.rememberSaveable
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
var BUTTON_COLOR = Color(200, 200, 200, 255)
var SCREEN_COLOR = Color(230, 230, 230, 255)
var MENU_COLOR = Color(240, 240, 240, 255)
var LOST_COLOR = Color(255, 179, 179, 255)
var WON_COLOR = Color(179, 255, 179, 255)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DrawRobotLayout()
        }
    }
}

data class State(var moves: MutableState< List< Triple<Int, Int, Int> > >)
data class Properties(val constraints: List<Int>)


fun getStarNumber(moveCount: Int, constraints: List<Int>): Int
{
    var stars = 0
    for (i in constraints.size - 1 downTo 0)
    {
        if (moveCount <= constraints[i])
        {
            stars = i + 1
            break
        }
    }
    return stars
}


fun stepUp(state: State)
{
    val last = state.moves.value.last()
    state.moves.value = listOf(
        *state.moves.value.toTypedArray(),
        Triple(last.first, last.second + 1, last.third))
}


@Composable
fun ColumnScope.Header()
{
    Box(Modifier.fillMaxWidth().height(50.dp)) {
        Box(Modifier.padding(DEFAULT_MARGIN).clip(RoundedCornerShape(5.dp))
                .background(LOST_COLOR)
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
fun RowScope.LeftBar(state: State, properties: Properties)
{
    val starNumber = getStarNumber(
        state.moves.value.size - 1, properties.constraints)

    fun drawRemainingMoves(): String
    {
        val constraint = properties.constraints[max(0, starNumber - 1)]
        return "${state.moves.value.size - 1}/$constraint"
    }

    Box(Modifier.fillMaxHeight()
            .width(MENU_WIDTH)) {
        Column {
            Box(Modifier.padding(DEFAULT_MARGIN)
                    .fillMaxWidth().height(30.dp)) {
                Row(Modifier.align(Alignment.Center)) {
                    for (i in 0 until starNumber)
                    {
                        Box(Modifier.fillMaxHeight()) {
                            Image(painter=painterResource(id=R.drawable.star),
                                  contentDescription="star",
                                  modifier=Modifier.align(Alignment.Center).size(STAR_SIZE),
                            )
                        }
                    }
                    // Box(Modifier.fillMaxHeight()) {
                    //     Image(painter=painterResource(id=R.drawable.star),
                    //           contentDescription="star",
                    //           modifier=Modifier.align(Alignment.Center).size(STAR_SIZE),
                    //     )
                    // }
                    // Box(Modifier.fillMaxHeight()) {
                    //     Image(painter=painterResource(id=R.drawable.star),
                    //           contentDescription="star",
                    //           modifier=Modifier.align(Alignment.Center).size(STAR_SIZE),
                    //     )
                    // }
                }
            }
            Box(Modifier.padding(start=DEFAULT_MARGIN, end=DEFAULT_MARGIN)

                    .fillMaxWidth().height(30.dp)) {
                Text(drawRemainingMoves(),
                     modifier=Modifier.align(Alignment.Center),
                     style=TextStyle(
                         fontSize=MOVES_TEXT_SIZE,
                         fontFamily=FONT_FAMILY,
                     ))
            }
            Spacer(Modifier.weight(1f))
            Button(
                { stepUp(state) },
                Modifier.padding(start=DEFAULT_MARGIN, end=DEFAULT_MARGIN)
                    .fillMaxWidth().aspectRatio(1f),
                shape=RoundedCornerShape(5.dp),
                colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
                contentPadding=PaddingValues(0.dp),
                enabled=starNumber > 0,
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

    Box(Modifier
            .padding(PaddingValues(end=8.dp))
            .border(width=0.8.dp, color=Color.Black, shape=RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .background(SCREEN_COLOR)
            .fillMaxHeight()
            .weight(1f)) {
        Column {
            Row(Modifier.fillMaxSize().weight(1f)) {
                Box(Modifier
                        .fillMaxSize().weight(1f))
                Box(Modifier
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
                            Text("(x=19; y=9)",
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
                Box(Modifier
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
                            Text("(x=0; y=0)",
                                 modifier=Modifier.align(Alignment.Center),
                                 style=TextStyle(
                                     fontSize=textSize,
                                     fontFamily=FONT_FAMILY,
                                 ),
                            )
                        }
                    }
                }
                Box(Modifier
                        .fillMaxSize().weight(1f))
            }
        }
    }
}




// @Composable
// fun ColumnScope.Body()
// {
//     Box(Modifier.weight(1f)
//             .fillMaxWidth()) {
//         Row {
//             LeftBar()
//             Screen()
//         }
//     }

// }


@Composable
fun ColumnScope.Footer()
{
    Box(Modifier.height(MENU_WIDTH)
            .fillMaxWidth()) {
        Row {
            Button(
                {},
                Modifier.padding(DEFAULT_MARGIN)
                    .fillMaxHeight().aspectRatio(1f),
                shape=RoundedCornerShape(5),
                colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
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
                colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
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
                colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
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
                colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
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




@Composable
fun ColumnScope.Body(state: State, properties: Properties)
{
    Box(Modifier.weight(1f)
            .fillMaxWidth()) {
        Row {
            LeftBar(state, properties)
            Screen()
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

    //var moves by state: State, stepUp: () -> Unit
    val state = State(rememberSaveable { mutableStateOf(listOf(Triple(0, 0, 1))) })
    val properties = Properties(listOf(13, 11, 10))

    //stepUp()

    Box(Modifier.background(MENU_COLOR).fillMaxSize()) {
        Column {
            Header()
            Body(state, properties)
            Footer()
        }
    }
}
