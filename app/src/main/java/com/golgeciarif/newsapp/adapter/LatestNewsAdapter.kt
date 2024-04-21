package com.golgeciarif.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.golgeciarif.newsapp.databinding.BreakingnewsRvItemBinding
import com.golgeciarif.newsapp.newsPojo.Article

class LatestNewsAdapter(): RecyclerView.Adapter<LatestNewsAdapter.NewsViewHolder>() {
    private lateinit var binding: BreakingnewsRvItemBinding
    private var breakingNewsList = ArrayList<Article>()


    var onItemClick : ((Article) -> Unit)? = null

    fun submitList(list: ArrayList<Article>){
        this.breakingNewsList= list
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding=BreakingnewsRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return breakingNewsList.size
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
       var news: Article = breakingNewsList[position]
        holder.binding.tvAuthor.text= news.author
        holder.binding.tvDate.text=news.publishedAt
        holder.binding.tvTitle.text= news.title
        holder.binding.tvCategory.text= "null"

        Glide.with(holder.itemView).load(news.urlToImage).into(holder.binding.ivNews)

        binding.root.setOnClickListener {
            onItemClick?.invoke(news)
        }

    }

    inner class NewsViewHolder(val binding: BreakingnewsRvItemBinding): ViewHolder(binding.root)

}

