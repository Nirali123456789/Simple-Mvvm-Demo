package com.example.myapplication.Viewmodels

import androidx.lifecycle.*
import com.example.myapplication.Models.Category
import com.example.myapplication.Repository.CategoryRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(private val foodItemRepository: CategoryRepo) : BaseViewModel() {
    private lateinit var data: ArrayList<Category>
    var mutableLiveData: ArrayList<Category>? = arrayListOf()


    fun getList(page: Int) {
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


    val allFoodItems: LiveData<MutableList<Category>> = foodItemRepository.alFoodItems.asLiveData()

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insertAll(foodItem: ArrayList<Category>) = viewModelScope.launch {
        //foodItemRepository?.insertAll(foodItem)
    }

//    class Factory(private val app: CategoryRepo) : ViewModelProvider.AndroidViewModelFactory() {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return CategoryViewModel(app) as T
//        }
//
//    }

    class CategoryViewModelFactory(val foodItemRepository: CategoryRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CategoryViewModel(foodItemRepository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }

    }

    fun Response():Boolean {
       return foodItemRepository.getresponse()
    }
}