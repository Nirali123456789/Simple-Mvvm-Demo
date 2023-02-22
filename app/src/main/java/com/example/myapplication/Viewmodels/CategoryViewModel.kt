package com.example.myapplication.Viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.Models.Category
import com.example.myapplication.Repository.CategoryRepo
import kotlinx.coroutines.*

class CategoryViewModel(private val foodItemRepository: CategoryRepo) : ViewModel() {

    class Factory(private val app: Application, private val repo: CategoryRepo) :
        ViewModelProvider.AndroidViewModelFactory(app) {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CategoryViewModel(repo) as T
        }

    }

    private lateinit var data: ArrayList<Category>
    var mutableLiveData: ArrayList<Category>? = arrayListOf()


    fun getList(page: Category) {
        CoroutineScope(Dispatchers.IO).launch {

            foodItemRepository.InsertData(page)
            foodItemRepository.getCallData()
        }

    }
fun postreq(category: Category)
{
    CoroutineScope(Dispatchers.IO).launch {
        foodItemRepository.RetrofitCallPost(category)
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


    val allFoodItems: LiveData<MutableList<Category>> = foodItemRepository.alFoodItems

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insertAll(foodItem: ArrayList<Category>) = viewModelScope.launch {
        //foodItemRepository?.insertAll(foodItem)
    }





    fun Response():Boolean {
       return foodItemRepository.getresponse()
    }
}