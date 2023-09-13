package com.volganl64.robotjump

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.room.Query
import androidx.room.Update


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
    @Insert
    fun init(vararg scores: Score)

    @Update
    fun update(score: Score)

    @Query("SELECT * FROM Score WHERE id=:id")
    fun get(id: Int): Score

    @Query("SELECT * FROM Score")
    fun getAll(): Array<Score>
}


@Database(entities=[Score::class], version=1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun scoreDao() : ScoreDao
}
