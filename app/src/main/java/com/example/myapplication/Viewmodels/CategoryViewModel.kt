package com.example.myapplication.Viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.Models.Jokes
import com.example.myapplication.Repository.CategoryRepo
import kotlinx.coroutines.*

class CategoryViewModel(private val foodItemRepository: CategoryRepo) : ViewModel() {

    class Factory(private val app: Application, private val repo: CategoryRepo) :
        ViewModelProvider.AndroidViewModelFactory(app) {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CategoryViewModel(repo) as T
        }

    }

    private lateinit var data: ArrayList<Jokes>
    var mutableLiveData: ArrayList<Jokes>? = arrayListOf()


    fun getList(page: Jokes) {
        CoroutineScope(Dispatchers.IO).launch {

            foodItemRepository.InsertData(page)
            foodItemRepository.getCallData()
        }

    }



    fun getSavedData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default)
            {
                //  mutableLiveData = mainCallerdata.getNotes()
            }

        }
    }


    val allFoodItems: LiveData<MutableList<Jokes>> = foodItemRepository.alFoodItems

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insertAll(list: ArrayList<Jokes>) = viewModelScope.launch {
        foodItemRepository.alFoodItems.value = list
    }


    fun Response(): Boolean {
        return foodItemRepository.getresponse()
    }
}