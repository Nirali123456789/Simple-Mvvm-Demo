package com.example.myapplication.Repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Models.Jokes
import com.example.myapplication.Retrofit.ApiInterface
import com.example.myapplication.RoomDatabase.CategoryDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepo(private val foodItemDao: CategoryDao, var page: Int = 1) {

    val alFoodItems: MutableLiveData<MutableList<Jokes>> = MutableLiveData()

}