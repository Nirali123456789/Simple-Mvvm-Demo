package com.example.myapplication.retrofit

import com.example.myapplication.models.Jokes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("Christmas")
    fun getJokes(@Query("amount") amount: Int,@Query("twopart") twopart:Int): Call<Jokes>

}