package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jokes")
class Jokes(
    @PrimaryKey
    var id: String = "00", var error: String = "", var amount: Int,
    var jokes: ArrayList<Joke>

)

class Joke {
    var setup: String = ""
    var delivery: String = ""
}





