package com.example.myapplication.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Joke
import com.example.myapplication.models.Jokes
import com.example.myapplication.databinding.PosterItemsBinding


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

