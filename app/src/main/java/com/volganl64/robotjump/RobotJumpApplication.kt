package com.volganl64.robotjump

import android.app.Application
import androidx.room.Room


class RobotJumpApplication : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        AppDatabase.init(this)
        // val instance = Room.databaseBuilder(
        //         applicationContext,
        //         AppDatabase::class.java, "robot-db",
        //     ).build()

    }
}
