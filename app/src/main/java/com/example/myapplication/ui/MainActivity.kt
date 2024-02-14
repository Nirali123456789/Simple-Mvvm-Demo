package com.example.myapplication.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.ThemeAdapter
import com.example.myapplication.App
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.models.Joke
import com.example.myapplication.models.Jokes
import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.viewmodels.CategoryViewModel
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity<ActivityMainBinding, CategoryViewModel>() {
    var list: ArrayList<Jokes> = arrayListOf()
    private var currentApiVersion = 0
    var arrlist: ArrayList<Joke> = arrayListOf()
    private lateinit var adapter: ThemeAdapter

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
        val call: Call<Jokes> = RetrofitClient.instance?.getMyApi()!!.getJokes(15,15)
        call.enqueue(object : Callback<Jokes> {
            override fun onResponse(
                call: Call<Jokes>,
                response: Response<Jokes>
            ) {
                Log.e("onResponse", "onResponse: ${response.body()}")
                val jokesList: Jokes = (response.body())!!
                list.add(jokesList)
                model.insertAll(list)


            }

            override fun onFailure(call: Call<Jokes>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "An error has occured${t.message}",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })



        model.allFoodItems.observe(
            this
        ) {
            Log.d("getJokes", "getJokes: ${it[0].jokes.size}")
            arrlist = it[0].jokes
            for (i in arrlist.indices)
            {
                Log.d("TAG", "onBindViewHolder: ${arrlist.get(i)}")
            }
            adapter.setData(arrlist)
        }
        setupData()

    }

    private fun setupData() {
        adapter = ThemeAdapter(this)
        var layoutManager: LinearLayoutManager = LinearLayoutManager(this)
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