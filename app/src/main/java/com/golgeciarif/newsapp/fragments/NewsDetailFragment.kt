package com.golgeciarif.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.golgeciarif.newsapp.databinding.NewsDetailsBinding
import com.golgeciarif.newsapp.newsPojo.Article

class NewsDetailFragment: Fragment() {
    private lateinit var binding: NewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= NewsDetailsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var article: Article? = arguments?.getParcelable("new")

        if (article!= null)
        {
            binding.tvNewsTitle.text = article.title
            binding.tvNewsAuthor.text=article.author
            binding.tvNewsDate.text=article.publishedAt
            binding.tvContent.text=article.content

            Glide.with(requireActivity()).load(article.urlToImage).into(binding.ivNews)
        }

    }

}