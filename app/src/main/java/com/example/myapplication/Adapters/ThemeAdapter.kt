package com.example.myapplication.Adapters

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.myapplication.Models.Joke
import com.example.myapplication.Models.Jokes
import com.example.myapplication.R
import com.example.myapplication.databinding.PosterItemsBinding
import java.util.Objects


class ThemeAdapter(var context: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: ArrayList<Joke> = arrayListOf()
    private val LOADING = 0
    private val ITEM = 1
    private var isLoadingAdded = false


    companion object ListItemCallback : DiffUtil.ItemCallback<Jokes>() {
        override fun areItemsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
            return oldItem.id == newItem.id
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = PosterItemsBinding.inflate(inflater, parent, false)
        return PosterViewHolder(binding)
    }

    fun setData(posterList: ArrayList<Joke>) {

        list = posterList!!
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder1: RecyclerView.ViewHolder, position: Int) {


        var holder: PosterViewHolder = holder1 as PosterViewHolder
        holder.setData(list.get(position), context)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PosterViewHolder(val view: PosterItemsBinding) :
        RecyclerView.ViewHolder(view.root) {


        fun setData(
            poster: Joke,
            context: Context) {

            Log.d("TAG", "setData: " + "onclick${poster.delivery}")
            view.setUp.text =poster.setup
            view.delievery.text =poster.delivery

        }
    }


}

