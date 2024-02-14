package com.example.myapplication.RoomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.myapplication.models.Jokes


@Dao
interface CategoryDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(Courses: ArrayList<Jokes>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodItem: Jokes)
      
}