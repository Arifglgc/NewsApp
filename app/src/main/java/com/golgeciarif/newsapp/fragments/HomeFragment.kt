package com.golgeciarif.newsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.golgeciarif.newsapp.util.NewsCategories
import com.golgeciarif.newsapp.adapter.LatestNewsAdapter
import com.golgeciarif.newsapp.adapter.NewsAdapter
import com.golgeciarif.newsapp.adapter.NewsCategoryAdapter
import com.golgeciarif.newsapp.databinding.FragmentHomeBinding
import com.golgeciarif.newsapp.newsPojo.Article
import com.golgeciarif.newsapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
private const val TAG= "HOMEFRAGMENT"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    private lateinit var categoryAdapter: NewsCategoryAdapter
    private lateinit var newsAdapter: NewsAdapter


    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        latestNewsAdapter= LatestNewsAdapter()
        categoryAdapter= NewsCategoryAdapter(0)
        binding= FragmentHomeBinding.inflate(layoutInflater)
        newsAdapter= NewsAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareBreakingNewsRecyclerView()
        prepareCategoriesRecyclerView()
        prepareNewsRecyclerView()

        viewModel.fetchLatestNews()
        Log.d(TAG, "test")

        fetchCategoryList()
        onLocationClick()

        onLatestNewClick()
        onNewsListClick()
        observerLatestList()
        observeNewsRecyclerview()

}

    private fun onNewsListClick() {
        latestNewsAdapter.onItemClick = { new ->


        }
    }

    private fun onLatestNewClick() {
        newsAdapter.onItemClick ={ new->


        }
    }

    private fun onLocationClick() {
        categoryAdapter.onItemClick = { name, position ->
            viewModel.viewModelScope.launch {
                Log.d(TAG, name)

                viewModel.fetchNewsByCategory(name)

                viewModel.setSelectedPosition(position)
                prepareNewsRecyclerView()
            }
        }
    }


    private fun observeNewsRecyclerview() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsByCategory.collect { articles ->
                    var newArrayList: ArrayList<Article> = ArrayList(articles)
                    newsAdapter.submitList(newArrayList)
                }
            }
        }
    }

    private fun fetchCategoryList() {
        categoryAdapter.setCategoryList(NewsCategories.categories)
    }

    private fun observerLatestList() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.latestNews.collect { articles ->
                    var newArrayList: ArrayList<Article> = ArrayList(articles)
                    latestNewsAdapter.submitList(newArrayList)
                }
            }
        }
    }


    private fun prepareNewsRecyclerView() {
        binding.rvNewsList.apply {
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter=newsAdapter
        }
    }

    private fun prepareCategoriesRecyclerView() {
        binding.rvNewsCategory.apply {
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter=categoryAdapter
        }
    }

    private fun prepareBreakingNewsRecyclerView() {
        binding.rvLatestNews.apply {
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter=latestNewsAdapter
        }
    }
}