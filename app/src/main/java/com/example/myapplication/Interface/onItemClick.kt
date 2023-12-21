package com.example.myapplication.Interface

import com.example.myapplication.Models.Jokes


interface onItemClick {
    fun onClick(itemData : Jokes, pos :Int)
}