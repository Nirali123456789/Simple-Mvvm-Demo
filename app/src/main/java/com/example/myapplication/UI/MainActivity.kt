package com.example.myapplication.UI

import android.R
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapters.ThemeAdapter
import com.example.myapplication.App
import com.example.myapplication.Base.BaseActivity
import com.example.myapplication.Models.Jokes
import com.example.myapplication.Retrofit.RetrofitClient
import com.example.myapplication.Viewmodels.CategoryViewModel
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity<ActivityMainBinding, CategoryViewModel>() {
    var list: ArrayList<Jokes> = arrayListOf()
    private var currentApiVersion = 0
    var arrlist: ArrayList<Jokes> = arrayListOf()
    private lateinit var adapter: ThemeAdapter

    companion object {
        private const val REQUEST_ID_BECOME_CALL_SCREENER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        currentApiVersion = Build.VERSION.SDK_INT
        super.onCreate(savedInstanceState)
        getJokes();


    }

    private fun getJokes() {
        val model: CategoryViewModel by viewModels() {
            CategoryViewModel.Factory(
                (application as App),
                (application as App).repository
            )
        }
        val call: Call<ArrayList<Jokes>> = RetrofitClient.instance?.getMyApi()!!.getJokes(10)
        call.enqueue(object : Callback<ArrayList<Jokes>> {
            override fun onResponse(
                call: Call<ArrayList<Jokes>>,
                response: Response<ArrayList<Jokes>>
            ) {
                Log.e("onResponse", "onResponse: ${response.body()}")
                val jokesList: ArrayList<Jokes> = (response.body())!!
//                val oneHeroes = arrayOfNulls<String>(myheroList.size)
//                for (i in myheroList.indices) {
//                    oneHeroes[i] = myheroList[i].t_name
//                }
                model.insertAll(jokesList)
//                superListView.setAdapter(
//                    ArrayAdapter<String?>(
//                        applicationContext,
//                        R.layout.simple_list_item_1,
//                        oneHeroes
//                    )
//                )
            }

            override fun onFailure(call: Call<ArrayList<Jokes>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "An error has occured${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })



        model.allFoodItems.observe(
            this
        ) {
            Log.d("getJokes", "getJokes: ${it[0].setup}")
            arrlist = it as ArrayList<Jokes>
            adapter.setData(arrlist)
        }
        setupData()

    }

    private fun setupData() {
        adapter = ThemeAdapter(this)
        var layoutManager: GridLayoutManager = GridLayoutManager(this, 2)
        binding.recyclerview.layoutManager =
            layoutManager
        binding.recyclerview.itemAnimator = DefaultItemAnimator()
        binding.recyclerview.adapter = adapter
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
    }


    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater!!);
    }


}