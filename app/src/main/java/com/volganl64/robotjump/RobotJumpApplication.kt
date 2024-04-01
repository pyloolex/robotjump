package com.volganl64.robotjump

import android.app.Application


class RobotJumpApplication : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        AppDatabase.init(this)
    }
}
