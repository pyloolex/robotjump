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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


private val TEXT_SIZE = 18.sp


@Preview
@Composable
fun RulesScreen()
{
    Box(Modifier.background(MENU_COLOR).padding(DEFAULT_MARGIN).fillMaxSize().verticalScroll(rememberScrollState())) {
        Column {
            Box(Modifier.fillMaxWidth())
            {
                Row(
                    Modifier,//.padding(10.dp),
                    //GridCells.Fixed(3),
                    //horizontalArrangement=Arrangement.spacedBy(0.dp)
                ) {
                    //item {
                        Box(Modifier
                                .aspectRatio(1f).fillMaxWidth().weight(1f))
                //}
                  //  item {
                        Box(Modifier.fillMaxWidth().aspectRatio(1f).weight(1f))
                        {
                            Image(
                                painter=painterResource(R.drawable.robotimage),
                                contentDescription="robot",
                                modifier=Modifier.align(Alignment.Center),
                            )
                        }
                //}
                  //  item {
                        Box(Modifier
                                .aspectRatio(1f).fillMaxWidth().weight(1f))
                //}
                }
            }
            Box(Modifier.fillMaxWidth())
            {
                Column(Modifier.fillMaxWidth()) {
                    Text(
                        "Attention! Use your device's \"Back\" button to navigate between screens within this app.",
                        Modifier.padding(top=DEFAULT_MARGIN,
                                         bottom=DEFAULT_MARGIN * 2),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                        fontWeight=FontWeight.Bold,
                    )

                    Text(
                        "RULES",
                        Modifier.fillMaxWidth(),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                        textAlign=TextAlign.Center,
                        fontWeight=FontWeight.Bold,
                    )

                    Text(
                        "You are given a coordinate plane and a robot " +
                            "standing at the point (0; 0). You aim to " +
                            "help the robot reach its final destination " +
                            "(x; y) in the least possible number of moves.",
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    Text(
                        "There are three types of moves:",
                        Modifier.padding(top=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    var annotatedString = buildAnnotatedString {
                        val str = StringBuilder()
                        str.append("1) Jump ")
                        val firstKpos = str.length
                        str.append("k cells right. The robot's position " +
                            "changes by increasing the value on " +
                            "the X axis: (x+")
                        val secondKpos = str.length
                        str.append("k; y).")
                        append(str.toString())

                        addStyle(
                            style=SpanStyle(fontWeight=FontWeight.Bold),
                            start=firstKpos,
                            end=firstKpos+1,
                        )
                        addStyle(
                            style=SpanStyle(fontWeight=FontWeight.Bold),
                            start=secondKpos,
                            end=secondKpos+1,
                        )
                    }
                    Text(
                        annotatedString,
                        Modifier.padding(start=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    annotatedString = buildAnnotatedString {
                        val str = StringBuilder()
                        str.append("2) Jump ")
                        val firstKpos = str.length
                        str.append("k cells up. The robot's position " +
                            "changes by increasing the value on " +
                            "the Y axis: (x; y+")
                        val secondKpos = str.length
                        str.append("k).")
                        append(str.toString())

                        addStyle(
                            style=SpanStyle(fontWeight=FontWeight.Bold),
                            start=firstKpos,
                            end=firstKpos+1,
                        )
                        addStyle(
                            style=SpanStyle(fontWeight=FontWeight.Bold),
                            start=secondKpos,
                            end=secondKpos+1,
                        )
                    }
                    Text(
                        annotatedString,
                        Modifier.padding(start=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    annotatedString = buildAnnotatedString {
                        val str = StringBuilder()
                        str.append(
                            "3) Increase the length of the jump. " +
                                "In other words, increase the "
                        )
                        val firstKpos = str.length
                        str.append("k by 1.")
                        append(str.toString())

                        addStyle(
                            style=SpanStyle(fontWeight=FontWeight.Bold),
                            start=firstKpos,
                            end=firstKpos+1,
                        )
                    }
                    Text(
                        annotatedString,
                        Modifier.padding(start=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    Text(
                        "The initial length of the jump (k) is 1.",
                        Modifier.padding(top=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    Text(
                        "Let's consider an example. Let the destination " +
                            "point be (3; 5).",
                        Modifier.padding(top=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    Text(
                        "The solution might be the following:\n" +
                            "1) Jump 1 cell up. " +
                            "Robot's coordinates: (0; 1).\n" +
                            "2) Jump 1 cell right. " +
                            "Robot's coordinates: (1; 1).\n" +
                            "3) Increase the length of the jump " +
                            " by one. Now the length of the jump is 2.\n" +
                            "4) Jump 2 cells right. " +
                            "Robot's coordinates: (3; 1).\n" +
                            "5) Jump 2 cells up. " +
                            "Robot's coordinates: (3; 3).\n" +
                            "6) Jump 2 cells up. " +
                            "Robot's coordinates: (3; 5).",
                        Modifier.padding(top=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    Box(Modifier.fillMaxWidth().padding(top=DEFAULT_MARGIN))
                    {
                        Image(
                            painter=painterResource(R.drawable.first_example),
                            contentDescription="firstExample",
                            modifier=Modifier.align(Alignment.Center).fillMaxWidth(0.8f),
                        )
                    }

                    Text(
                        "Therefore, the robot has reached its " +
                            "destination in 6 moves. But is it " +
                            "the best solution? Or is it " +
                            "possible to reach it in fewer moves?",
                        Modifier.padding(top=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    Text(
                        "Yeah, it's possible! The best solution " +
                            "includes only 5 moves and looks like this:\n" +
                            "1) Increase the length of the jump by one. " +
                            "Now the length of the jump is 2.\n" +
                            "2) Jump 2 cells up. " +
                            "Robot's coordinates: (0; 2).\n" +
                            "3) Increase the length of the " +
                            "jump by one. Now the length of " +
                            "the jump is 3.\n" +
                            "4) Jump 3 cells right. " +
                            "Robot's coordinates: (3; 2).\n" +
                            "5) Jump 3 cells up. " +
                            "Robot's coordinates: (3; 5).",
                        Modifier.padding(top=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                    Box(Modifier.fillMaxWidth().padding(top=DEFAULT_MARGIN))
                    {
                        Image(
                            painter=painterResource(R.drawable.second_example),
                            contentDescription="secondExample",
                            modifier=Modifier.align(Alignment.Center).fillMaxWidth(0.8f),
                        )
                    }

                    Text(
                        "Go ahead and pass all the levels with the " +
                            "least number of moves! Good luck!",
                        Modifier.padding(top=DEFAULT_MARGIN),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=TEXT_SIZE,
                        ),
                    )

                }
            }
        }
    }
}
