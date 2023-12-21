package com.example.myapplication.Retrofit

import com.example.myapplication.Models.Jokes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {


    @GET("Programming")
    fun getJokes(@Query("amount") amount: Int): Call<ArrayList<Jokes>>

}