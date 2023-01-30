package com.example.myapplication.Base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.example.myapplication.Viewmodels.BaseViewModel


public abstract class BaseActivity<Binding :ViewDataBinding ,vm: BaseViewModel> : AppCompatActivity() {
    protected var viewModel: vm? = null
    protected lateinit var binding: Binding
    protected abstract fun createViewModel(): vm

    protected abstract fun createViewBinding(layoutInflater: LayoutInflater?): Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createViewBinding(LayoutInflater.from(this))
        setContentView(binding!!.getRoot())
        viewModel = createViewModel()
    }
}