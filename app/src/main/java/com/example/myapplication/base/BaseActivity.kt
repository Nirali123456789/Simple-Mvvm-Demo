package com.example.myapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


public abstract class BaseActivity<Binding :ViewDataBinding ,vm: ViewModel> : AppCompatActivity() {
    protected var viewModel: vm? = null
    protected lateinit var binding: Binding


    protected abstract fun createViewBinding(layoutInflater: LayoutInflater?): Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createViewBinding(LayoutInflater.from(this))
        setContentView(binding!!.getRoot())

    }
}