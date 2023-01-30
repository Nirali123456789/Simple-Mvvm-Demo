package com.example.myapplication.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "Category")
 class Category(
  @PrimaryKey
  var t_id: String = "00",
   var t_video: String = "",


   var t_thumb: String = "",

   var t_name: String = "")





