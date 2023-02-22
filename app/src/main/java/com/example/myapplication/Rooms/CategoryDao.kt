package com.example.myapplication.RoomDatabase

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Models.Category
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(Courses: ArrayList<Category>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodItem: Category)
      
}