package com.golgeciarif.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.golgeciarif.newsapp.databinding.CategoriesButtonItemBinding
import com.golgeciarif.newsapp.model.NewsCategory

class NewsCategoryAdapter(private var selectedPosition: Int): RecyclerView.Adapter<NewsCategoryAdapter.CategoriesViewHolder>() {
    private lateinit var binding: CategoriesButtonItemBinding
    private var newsCategoriesList = ArrayList<NewsCategory>()


    var onItemClick : ((String,Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding=CategoriesButtonItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriesViewHolder(binding)
    }

    fun setCategoryList(list: ArrayList<NewsCategory>){
        this.newsCategoriesList= list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return newsCategoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
       var catagory: NewsCategory = newsCategoriesList[position]
        binding.button.text= catagory.name

        binding.button.setOnClickListener {
            selectedPosition= holder.adapterPosition
            //notifyDataSetChanged()
            onItemClick!!.invoke(catagory.name as String,selectedPosition)

        }
    }

    inner class CategoriesViewHolder(val binding: CategoriesButtonItemBinding): ViewHolder(binding.root)

}

