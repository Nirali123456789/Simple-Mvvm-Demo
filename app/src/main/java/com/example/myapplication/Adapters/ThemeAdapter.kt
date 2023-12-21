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
import com.example.myapplication.Models.Jokes
import com.example.myapplication.R
import com.example.myapplication.databinding.PosterItemsBinding


class ThemeAdapter(var context: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: ArrayList<Jokes> = arrayListOf()
    private val LOADING = 0
    private val ITEM = 1
    private var isLoadingAdded = false


    companion object ListItemCallback : DiffUtil.ItemCallback<Jokes>() {
        override fun areItemsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
            return oldItem.setup == newItem.setup
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            ITEM -> {
                val binding = PosterItemsBinding.inflate(inflater, parent, false)
                return PosterViewHolder(binding)


            }

        }


        val binding = PosterItemsBinding.inflate(inflater, parent, false)
        return PosterViewHolder(binding)
    }

    fun setData(posterList: ArrayList<Jokes>) {

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


    override fun getItemViewType(position: Int): Int {
        return if (position == list.size - 1 && isLoadingAdded) LOADING else ITEM
    }


    fun add(movie: Jokes) {
        list.add(movie)
        notifyItemInserted(list.size - 1)
    }


    class PosterViewHolder(val view: PosterItemsBinding) :
        RecyclerView.ViewHolder(view.root) {


        fun setData(
            poster: Jokes,
            context: Context

        ) {
            view.li1.setOnClickListener {
                Log.d("TAG", "setData: " + "onclick")
                //onItemClick1.onClick(poster, adapterPosition)


            }
            view.delievery.text =poster.delivery
            view.setUp.text =poster.setup
//            Glide.with(context).asBitmap().load(poster.t_thumb)
//                .centerCrop()
//
//                .placeholder(R.drawable.ic_placeholder)
//                .apply(RequestOptions().dontAnimate())
//                .listener(object : RequestListener<Bitmap?> {
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: Target<Bitmap?>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        Log.d("TAG", "setData: " + e!!.message)
//                        return true
//                    }
//
//
//                    override fun onResourceReady(
//                        resource: Bitmap?,
//                        model: Any?,
//                        target: Target<Bitmap?>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        Log.d("TAG", "setData: " + "loadedd" + poster.t_thumb)
//                        return false
//                    }
//
//                }).diskCacheStrategy(DiskCacheStrategy.ALL).into(view.imgPoster)


        }
    }


}

