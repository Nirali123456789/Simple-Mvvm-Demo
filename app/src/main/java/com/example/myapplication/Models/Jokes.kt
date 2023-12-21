package com.example.myapplication.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jokes")
class Jokes(
    @PrimaryKey
    var id: String = "00", var setup: String = "",
    var delivery: String = ""

)





