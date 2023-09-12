package com.volganl64.robotjump

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="Score")
data class Score(
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: Int,

    @ColumnInfo(name="stars")
    val stars: Int
)


@Dao
interface ScoreDao
{
    //fun add()
}
