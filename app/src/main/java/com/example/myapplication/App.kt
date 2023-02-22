package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.myapplication.Repository.CategoryRepo
import com.example.myapplication.RoomDatabase.CallDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @JvmStatic
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { CallDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { CategoryRepo(database.categorydao) }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }
}