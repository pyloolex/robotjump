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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


@Preview
@Composable
fun RulesScreen()
{
    Box(Modifier.background(MENU_COLOR).padding(DEFAULT_MARGIN).fillMaxSize()) {
        Column {
            Box(Modifier.fillMaxWidth())
            {
                LazyVerticalGrid(
                    GridCells.Fixed(3),
                    Modifier,//.padding(10.dp),
                    horizontalArrangement=Arrangement.spacedBy(0.dp)
                ) {
                    item {
                        Box(Modifier
                                .aspectRatio(1f).fillMaxWidth())
                    }
                    item {
                        Box(Modifier.fillMaxHeight().aspectRatio(1f))
                        {
                            Image(
                                painter=painterResource(R.drawable.robotimage),
                                contentDescription="robot",
                                modifier=Modifier.align(Alignment.Center),
                            )
                        }
                    }
                    item {
                        Box(Modifier
                                .aspectRatio(1f).fillMaxWidth())
                    }
                }
            }
            Box(Modifier.fillMaxWidth().verticalScroll(rememberScrollState()))
            {
                Column(Modifier.fillMaxWidth()) {
                    Text(
                        "Attention! Use your device's \"Back\" button to navigate between screens within this app.",
                        Modifier.padding(top=DEFAULT_MARGIN,
                                         bottom=DEFAULT_MARGIN * 2),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=17.sp,
                        ),
                        fontWeight=FontWeight.Bold,
                    )

                    Text(
                        "RULES",
                        Modifier.fillMaxWidth(),
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=17.sp,
                        ),
                        textAlign=TextAlign.Center,
                        fontWeight=FontWeight.Bold,
                    )

                    Text(
                        "TODO",
                        style=TextStyle(
                            fontFamily=FONT_FAMILY,
                            fontSize=17.sp,
                        ),
                    )
                }
            }
        }
    }
}
