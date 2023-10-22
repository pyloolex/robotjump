package com.volganl64.robotjump

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


@Composable
fun LevelsScreen(navigation: NavController)
{
    AppDatabase.instance.scoreDao().deleteAll()
    AppDatabase.instance.scoreDao().init()
    Box(Modifier.background(Color.Blue).fillMaxSize()) {
        LazyColumn {
            itemsIndexed(Levels) { i, level ->
                Button(
                    { navigation.navigate("game/$i") },
                    Modifier.padding(DEFAULT_MARGIN).height(70.dp).width(70.dp),
                    shape=RoundedCornerShape(5),
                    colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
                    contentPadding=PaddingValues(0.dp),
                ) {
                    Text("${i + 1} (0)")
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
