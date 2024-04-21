package com.golgeciarif.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.golgeciarif.newsapp.databinding.NewslistRvItemBinding
import com.golgeciarif.newsapp.newsPojo.Article

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private lateinit var binding: NewslistRvItemBinding
    private var newsList = ArrayList<Article>()

    fun submitList(list: ArrayList<Article>){
        newsList.clear() // Mevcut haberleri temizle
        newsList.addAll(list) // Yeni haberleri ekle
        notifyDataSetChanged()
    }

     var onItemClick : ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding= NewslistRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var news: Article = newsList[position]
        holder.binding.tvAuthor.text= news.author
        holder.binding.tvDate.text=news.publishedAt
        holder.binding.tvTitle.text= news.title
        holder.binding.tvCategory.text= "null"
        Glide.with(holder.itemView).load(news.urlToImage).into(holder.binding.ivNews)

        binding.root.setOnClickListener {
            onItemClick?.invoke(news)
        }
    }
    inner class NewsViewHolder(val binding: NewslistRvItemBinding): RecyclerView.ViewHolder(binding.root)
}