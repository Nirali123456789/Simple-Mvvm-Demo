package com.example.myapplication.converters

import androidx.room.TypeConverter
import com.example.myapplication.models.Joke
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<Joke>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Joke>::class.java).toList()
}