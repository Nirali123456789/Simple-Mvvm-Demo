package com.example.myapplication.Adapters

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.colorcall_new.R
import com.example.myapplication.Utility.Category
import com.example.myapplication.Intefaces.onItemClick
import com.example.colorcall_new.databinding.ItemProgressBinding
import com.example.colorcall_new.databinding.PosterItemsBinding


class ThemeAdapter(var context: Activity, var onItemClick1: onItemClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: ArrayList<Category> = arrayListOf()
    private val LOADING = 0
    private val ITEM = 1
    private var isLoadingAdded = false


    companion object ListItemCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.t_name == newItem.t_name
        }
    }

//    companion object {
//        var adType: Int = 1
//        var postType: Int = 0
//    }

    fun setOnClickListener(onItemClick: onItemClick) {
        onItemClick1 = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
         when (viewType) {
            ITEM -> {
                val binding = PosterItemsBinding.inflate(inflater, parent, false)
                return PosterViewHolder(binding, onItemClick1)


            }
            LOADING -> {
                val binding = ItemProgressBinding.inflate(inflater, parent, false)
                return LoadingViewHolder(binding)
            }
        }


        val binding = PosterItemsBinding.inflate(inflater, parent, false)
        return PosterViewHolder(binding, onItemClick1)
    }

    fun setData(posterList: ArrayList<Category>) {

        // list = arrayListOf()
        list= posterList!!
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder1: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM -> {
                var holder: PosterViewHolder = holder1 as PosterViewHolder

                holder.setData(list.get(position), context,  onItemClick1)


            }
            LOADING -> {
                val loadingViewHolder = holder1 as LoadingViewHolder
                loadingViewHolder.progressBar.visibility = View.VISIBLE
            }
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == list.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(Category())
    }
    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position: Int = list.size - 1
        val result: Category = list.get(list.size-1)
        if (result != null) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun add(movie: Category) {
        list.add(movie)
     //   Log.d("TAG", "onSucces: " +  movie.t_name+">>"+list.size  )
        notifyItemInserted(list.size - 1)
    }

    fun addAll(moveResults: List<Category>) {
        //list.addAll(moveResults)
    //    notifyDataSetChanged()
        for (result in moveResults) {
            add(result)
        }
    }

    class LoadingViewHolder(itemView: ItemProgressBinding) : RecyclerView.ViewHolder(itemView.root) {
        public val progressBar: ProgressBar

        init {
            progressBar = itemView.loadmoreProgress
        }
    }

    class PosterViewHolder(val view: PosterItemsBinding, onItemClick1: onItemClick) :
        RecyclerView.ViewHolder(view.root) {


        fun setData(
            poster: Category,
            context: Context, onItemClick1: onItemClick

        ) {
            Log.d("TAG", "setData:1223 " + poster.t_name)
            view.li1.setOnClickListener {
                Log.d("TAG", "setData: " + "onclick")
                onItemClick1.onClick(poster, adapterPosition)


            }
            Glide.with(context).asBitmap().load(poster.t_thumb)
                .centerCrop()

                .placeholder(R.drawable.ic_placeholder)
                .apply(RequestOptions().dontAnimate())
                .listener(object : RequestListener<Bitmap?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("TAG", "setData: " + e!!.message)
                        return true
                    }



                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("TAG", "setData: " + "loadedd"+poster.t_thumb)
                        return false
                    }

                }).diskCacheStrategy(DiskCacheStrategy.ALL).into(view.imgPoster)


        }
    }




}

