package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.models.Jokes
import com.example.myapplication.repository.CategoryRepo
import kotlinx.coroutines.*

class CategoryViewModel(private val foodItemRepository: CategoryRepo) : ViewModel() {
    val allFoodItems: LiveData<MutableList<Jokes>> = foodItemRepository.alFoodItems

    class Factory(private val app: Application, private val repo: CategoryRepo) :
        ViewModelProvider.AndroidViewModelFactory(app) {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CategoryViewModel(repo) as T
        }

    }

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insertAll(list: ArrayList<Jokes>) = viewModelScope.launch {
        foodItemRepository.alFoodItems.value = list
    }


}