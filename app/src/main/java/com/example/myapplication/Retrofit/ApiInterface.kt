package com.example.myapplication.Retrofit

import com.example.myapplication.Models.Jokes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {


    @GET("Christmas")
    fun getJokes(@Query("amount") amount: Int,@Query("twopart") twopart:Int): Call<Jokes>

}