package com.example.myapplication.UI

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapters.ThemeAdapter
import com.example.myapplication.App
import com.example.myapplication.Base.BaseActivity
import com.example.myapplication.Models.Category
import com.example.myapplication.R
import com.example.myapplication.Viewmodels.CategoryViewModel
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding, CategoryViewModel>() {
    var list: ArrayList<Category> = arrayListOf()
    private var currentApiVersion = 0
    lateinit var model: CategoryViewModel
    var arrlist: ArrayList<Category> = arrayListOf()
    private lateinit var adapter: ThemeAdapter

    companion object {
        private const val REQUEST_ID_BECOME_CALL_SCREENER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        currentApiVersion = Build.VERSION.SDK_INT
        val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = flags
            val decorView = window.decorView
            decorView.setOnSystemUiVisibilityChangeListener { visibility ->
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    decorView.systemUiVisibility = flags
                }
            }
        }
        super.onCreate(savedInstanceState)


       // binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val model: CategoryViewModel by viewModels() {
            CategoryViewModel.Factory(
                (application as App),
                (application as App).repository
            )
        }
        var category:Category = Category()
        category.t_name="Nature1"
        category.t_thumb="This is First Pic"
       model.postreq(category)
        model.allFoodItems.observe(
            this) {
            arrlist = it as ArrayList<Category>
            adapter.setData(arrlist)
        }
        SetupData()

    }

    private fun SetupData() {
        var layoutManager: GridLayoutManager
        adapter = ThemeAdapter(this)
        layoutManager = GridLayoutManager(this, 2)
        binding.recyclerview.layoutManager =
            layoutManager
        binding.recyclerview.itemAnimator = DefaultItemAnimator()
        binding.recyclerview.adapter = adapter
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
    }

//    override fun createViewModel(): CategoryViewModel {
//        model:CategoryViewModel by viewModels() {
//            CategoryViewModel.CategoryViewModelFactory((application as App).repository)
//        }
//        return  model
//    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater!!);
    }

}