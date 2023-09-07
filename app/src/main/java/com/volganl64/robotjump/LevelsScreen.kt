package com.volganl64.robotjump

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun LevelsScreen(navigation: NavController)
{
    Box(Modifier.background(Color.Blue).fillMaxSize()) {
        Button(
                { navigation.navigate("game") },
                Modifier.padding(DEFAULT_MARGIN).height(30.dp).width(30.dp),
                shape=RoundedCornerShape(5),
                colors=ButtonDefaults.buttonColors(containerColor=BUTTON_COLOR),
                contentPadding=PaddingValues(0.dp),
        ) {}
    }
}
