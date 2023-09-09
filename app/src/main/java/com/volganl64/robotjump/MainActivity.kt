package com.volganl64.robotjump

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController=navController, startDestination="levels")
            {
                composable("levels")
                {
                    LevelsScreen(navigation=navController)
                }
                composable(
                    "game/{level_idx}",
                    arguments=listOf(
                        navArgument("level_idx") { type=NavType.IntType },
                    )
                )
                { backStackEntry ->
                    GameScreen(navigation=navController,
                               backStackEntry.arguments!!.getInt("level_idx"))
                }
            }
        }
    }
}
