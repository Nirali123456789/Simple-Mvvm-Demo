package com.example.myapplication.Repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Models.Category
import com.example.myapplication.R
import com.example.myapplication.Retrofit.ApiInterface
import com.example.myapplication.Retrofit.ServiceBuilder
import com.example.myapplication.RoomDatabase.CategoryDao
import org.json.JSONException
import org.json.JSONObject
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepo(private val foodItemDao: CategoryDao, var page: Int = 1) {

    val TAG = javaClass.simpleName
    var mutableList: MutableLiveData<List<Category>> = MutableLiveData()
    var arrayList: ArrayList<Category> = arrayListOf()
    val alFoodItems: MutableLiveData<MutableList<Category>> = MutableLiveData()
    var emptyResponse = false

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(foodItem: Category) {
        foodItemDao.insert(foodItem)
    }

    fun getresponse(): Boolean {
        return emptyResponse
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(foodItem: ArrayList<Category>) {
        foodItemDao.insertAll(foodItem)
    }

    fun InsertData(page: Category) {
        //   arrayList= arrayListOf()
        RetrofitCall(page)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getCallData() {
        Log.d(TAG, "getCallData: " + arrayList.size)


        if (arrayList.size == 0) {
            emptyResponse = true
            // var li:ArrayList<Category> = arrayListOf()
            //    li.add(Category())
        } else {
            foodItemDao.insertAll(arrayList)
        }
    }

    fun RetrofitCall(category: Category) {
        val response = ServiceBuilder.buildService(ApiInterface::class.java)
        response.sendReq(category).enqueue(
            object : Callback<Category> {
                override fun onResponse(
                    call: Call<Category>,
                    response: Response<Category>
                ) {
                    Log.d(TAG, "onFailure: "+response.message())
                }

                override fun onFailure(call: Call<Category>, t: Throwable) {
                    Log.d(TAG, "onFailure: "+t.message)
                }

            }
        )

    }
    fun RetrofitCallPost(category: Category) {
        val response = ServiceBuilder.buildService(ApiInterface::class.java)
        response.sendReq(category).enqueue(
            object : Callback<Category> {
                override fun onResponse(
                    call: Call<Category>,
                    response: Response<Category>
                ) {
                    Log.d(TAG, "onFailure: "+response.message())
                }

                override fun onFailure(call: Call<Category>, t: Throwable) {
                    Log.d(TAG, "onFailure: "+t.message)
                }

            }
        )

    }

}