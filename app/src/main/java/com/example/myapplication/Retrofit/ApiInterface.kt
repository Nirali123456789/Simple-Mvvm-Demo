package com.example.myapplication.Retrofit

import com.example.myapplication.Models.Category
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/v1/create")
    fun sendReq(@Body requestModel: Category) : Call<Category>
}