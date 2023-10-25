package com.volganl64.robotjump

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Room
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
    fun insert(vararg scores: Score)

    @Update
    fun update(score: Score)

    @Query("SELECT * FROM Score WHERE id=:id")
    fun get(id: Int): Score

    @Query("SELECT * FROM Score")
    fun getAll(): Array<Score>

    @Query("DELETE FROM Score")
    fun deleteAll()

    fun init()
    {
        this.insert(*Array(Levels.size) { Score(it, 0) })
    }
}


@Database(entities=[Score::class], version=1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun scoreDao() : ScoreDao

    companion object
    {
        lateinit var instance : AppDatabase
        fun init(applicationContext : Context)
        {
            instance = (
                Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "robot-db"
                ).createFromAsset("database/robot-db.db")
                    .allowMainThreadQueries().build()
            )
        }
    }
}
