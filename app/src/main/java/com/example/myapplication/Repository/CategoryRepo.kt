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

    val TAG = javaClass.simpleName
    var mutableList: MutableLiveData<List<Jokes>> = MutableLiveData()
    var arrayList: ArrayList<Jokes> = arrayListOf()
    val alFoodItems: MutableLiveData<MutableList<Jokes>> = MutableLiveData()
    var emptyResponse = false

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(foodItem: Jokes) {
        foodItemDao.insert(foodItem)
    }

    fun getresponse(): Boolean {
        return emptyResponse
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(foodItem: ArrayList<Jokes>) {
        foodItemDao.insertAll(foodItem)
    }

    fun InsertData(page: Jokes) {
        //   arrayList= arrayListOf()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getCallData() {
        Log.d(TAG, "getCallData: " + arrayList.size)


        if (arrayList.size == 0) {
            emptyResponse = true
            // var li:ArrayList<Jokes> = arrayListOf()
            //    li.add(Jokes())
        } else {
            foodItemDao.insertAll(arrayList)
        }
    }




}