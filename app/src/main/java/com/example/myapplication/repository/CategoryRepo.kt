package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.models.Jokes
import com.example.myapplication.RoomDatabase.CategoryDao

class CategoryRepo(private val foodItemDao: CategoryDao, var page: Int = 1) {

    val alFoodItems: MutableLiveData<MutableList<Jokes>> = MutableLiveData()

}