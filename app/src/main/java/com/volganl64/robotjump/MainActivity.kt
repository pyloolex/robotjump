package com.volganl64.robotjump

import android.os.Bundle
import android.util.Log
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
        //AppDatabase.init(this)
        //Log.i("jj", AppDatabase.instance.scoreDao().getAll().contentToString())

        setContent {
            val navController = rememberNavController()
            NavHost(navController=navController, startDestination="rules") //"greeting")
            {
                composable("greeting")
                {
                    GreetingScreen(navigation=navController)
                }
                composable("rules")
                {
                    RulesScreen()
                }
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
