package com.volganl64.robotjump

import kotlin.math.max

import android.util.Log

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


private var HEADER_HEIGHT = 50.dp
private var HEADER_TEXT_SIZE = 0.sp // To be overwritten inside Composable.
private val MENU_WIDTH = 80.dp
private val STAR_SIZE = 20.dp
private var MOVES_TEXT_SIZE = 0.sp // To be overwritten inside Composable.
private var BUTTON_COLOR = Color(200, 200, 200, 255)
private var SCREEN_COLOR = Color(230, 230, 230, 255)
private var LOST_COLOR = Color(255, 179, 179, 255)
private var WON_COLOR = Color(179, 255, 179, 255)

enum class GAME_STATE {
    IN_PROGRESS,
    WON,
    TOO_MANY_MOVES,
    BEYOND_TARGET,
}

data class State(var moves: MutableState< List< Triple<Int, Int, Int> > >)


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


fun getGameState(state: State, properties: LevelProperties) : GAME_STATE
{
    val position = state.moves.value.last()
    if (position.first == properties.target.first &&
            position.second == properties.target.second)
    {
        return GAME_STATE.WON
    }
    if (position.first > properties.target.first ||
            position.second > properties.target.second)
    {
        return GAME_STATE.BEYOND_TARGET
    }
    if (getStarNumber(state.moves.value.size - 1, properties.constraints) == 0)
    {
        return GAME_STATE.TOO_MANY_MOVES
    }
    return GAME_STATE.IN_PROGRESS
}


fun checkMove(state: State, properties: LevelProperties,
              levelIdx: Int, bestScore: Int)
{
    val stars = getStarNumber(state.moves.value.size - 1,
                              properties.constraints)
    if (getGameState(state, properties) == GAME_STATE.WON &&
            stars > bestScore)
    {
        AppDatabase.instance.scoreDao().update(Score(levelIdx, stars))
    }
}


fun stepUp(state: State, properties: LevelProperties,
           levelIdx: Int, bestScore: Int)
{
    val last = state.moves.value.last()
    state.moves.value = listOf(
        *state.moves.value.toTypedArray(),
        Triple(last.first, last.second + last.third, last.third))
    checkMove(state, properties, levelIdx, bestScore)
}


fun stepRight(state: State, properties: LevelProperties,
              levelIdx: Int, bestScore: Int)
{
    val last = state.moves.value.last()
    state.moves.value = listOf(
        *state.moves.value.toTypedArray(),
        Triple(last.first + last.third, last.second, last.third))
    checkMove(state, properties, levelIdx, bestScore)
}

fun revertLast(state: State)
{
    if (state.moves.value.size > 1)
    {
        state.moves.value = state.moves.value.dropLast(1)
    }
}


fun restartGame(state: State)
{
    state.moves.value = listOf(Triple(0, 0, 1))
}


fun increaseJump(state: State)
{
    val last = state.moves.value.last()
    state.moves.value = listOf(
        *state.moves.value.toTypedArray(),
        Triple(last.first, last.second, last.third + 1))
}


@Composable
fun ColumnScope.Header(state: State, properties: LevelProperties)
{
    fun buildMessage(gameState: GAME_STATE) : String
    {
        if (gameState == GAME_STATE.WON)
        {
            return "You won!"
        }

        val reason: String = if (gameState == GAME_STATE.TOO_MANY_MOVES)
        {
            "too many moves"
        }
        else
        {
            assert(gameState == GAME_STATE.BEYOND_TARGET)
            "beyond the target"
        }

        return "You lost: $reason."
    }

    Box(Modifier.fillMaxWidth().height(HEADER_HEIGHT)) {
        val gameState = getGameState(state, properties)
        if (gameState != GAME_STATE.IN_PROGRESS)
        {
            Box(Modifier.padding(DEFAULT_MARGIN).clip(RoundedCornerShape(5.dp))
                    .background(
                        if (gameState == GAME_STATE.WON) WON_COLOR else LOST_COLOR)
                    .fillMaxSize(),
                contentAlignment=Alignment.Center) {
                Row {
                    Text(buildMessage(gameState),
                         style=TextStyle(
                             fontSize=HEADER_TEXT_SIZE,
                             fontFamily=FONT_FAMILY,
                         )
                    )
                }
            }
        }
    }
}


@Composable
fun RowScope.LeftBar(state: State, properties: LevelProperties,
                     levelIdx: Int, bestScore: Int)
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
            Box(
                Modifier.padding(start=DEFAULT_MARGIN, end=DEFAULT_MARGIN)
                    .fillMaxWidth().aspectRatio(1f)
            )
            {
                Image(
                    painter=painterResource(R.drawable.robotimage),
                    contentDescription="robot",
                    modifier=Modifier.fillMaxSize(),
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                { stepUp(state, properties, levelIdx, bestScore) },
                Modifier.padding(start=DEFAULT_MARGIN, end=DEFAULT_MARGIN)
                    .fillMaxWidth().aspectRatio(1f),
                shape=RoundedCornerShape(5.dp),
                colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
                contentPadding=PaddingValues(0.dp),
                enabled=getGameState(state, properties) == GAME_STATE.IN_PROGRESS,
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
                            "${state.moves.value.last().third}",
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
fun RowScope.Screen(state: State, properties: LevelProperties)
{
    val textSize = with(LocalDensity.current) {
        min(
            (LocalConfiguration.current.screenWidthDp.dp - MENU_WIDTH) / 12,
            (LocalConfiguration.current.screenHeightDp.dp - MENU_WIDTH -
                 HEADER_HEIGHT) / 10,
        ).toSp()
    }

    Log.e("jj", ((LocalConfiguration.current.screenWidthDp.dp - MENU_WIDTH) / 12).toString() + ":::::" + ((LocalConfiguration.current.screenHeightDp.dp - MENU_WIDTH -
                 HEADER_HEIGHT) / 10).toString())

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
                            Text("(x=${properties.target.first}; y=${properties.target.second})",
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
                            Text("(x=${state.moves.value.last().first}; y=${state.moves.value.last().second})",
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


@Composable
fun ColumnScope.Body(state: State, properties: LevelProperties,
                     levelIdx: Int, bestScore: Int)
{
    Box(Modifier.weight(1f)
            .fillMaxWidth()) {
        Row {
            LeftBar(state, properties, levelIdx, bestScore)
            Screen(state, properties)
        }
    }
}


@Composable
fun ColumnScope.Footer(state: State, properties: LevelProperties,
                       levelIdx: Int, bestScore: Int)
{
    Box(Modifier.height(MENU_WIDTH)
            .fillMaxWidth()) {
        Row {
            Button(
                { increaseJump(state) },
                Modifier.padding(DEFAULT_MARGIN)
                    .fillMaxHeight().aspectRatio(1f),
                shape=RoundedCornerShape(5),
                colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
                contentPadding=PaddingValues(0.dp),
                enabled=getGameState(state, properties) == GAME_STATE.IN_PROGRESS,
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
                { stepRight(state, properties, levelIdx, bestScore) },
                Modifier.padding(top=DEFAULT_MARGIN, bottom=DEFAULT_MARGIN)
                    .fillMaxHeight().aspectRatio(1f),
                shape=RoundedCornerShape(5),
                colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
                contentPadding=PaddingValues(0.dp),
                enabled=getGameState(state, properties) == GAME_STATE.IN_PROGRESS,
            ) {
                Row {
                    Box(Modifier.fillMaxHeight()) {
                        Text(
                            "${state.moves.value.last().third}",
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
                { revertLast(state) },
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
                { restartGame(state) },
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
fun DrawGame(levelIdx: Int, bestScore: Int)
{
    MOVES_TEXT_SIZE = with(LocalDensity.current) {
        16.dp.toSp()
    }
    HEADER_TEXT_SIZE = with(LocalDensity.current) {
        HEADER_TEXT_SIZE_BASE.toSp()
    }

    val state = State(rememberSaveable { mutableStateOf(listOf(Triple(0, 0, 1))) })
    val properties = Levels[levelIdx]
    //AppDatabase.instance.scoreDao().update(Score(levelIdx, 3))
    //Log.e("jj all", AppDatabase.instance.scoreDao().getAll().contentToString())

    Box(Modifier.background(MENU_COLOR).fillMaxSize()) {
        Column {
            Header(state, properties)
            Body(state, properties, levelIdx, bestScore)
            Footer(state, properties, levelIdx, bestScore)
        }
    }
}


@Composable
fun GameScreen(navigation: NavController, levelIdx: Int)
{
    val bestScore = AppDatabase.instance.scoreDao().get(levelIdx).stars
    DrawGame(levelIdx, bestScore)
}


@Preview
@Composable
fun GamePreview()
{
    val navController = rememberNavController()
    DrawGame(2, 3)
}
