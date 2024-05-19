package com.golgeciarif.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.golgeciarif.newsapp.adapter.NewsAdapter
import com.golgeciarif.newsapp.databinding.FragmentExploreBinding
import com.golgeciarif.newsapp.viewmodel.ExploreViewModel


class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding
    private lateinit var newsAdapter: NewsAdapter


    private val viewModel: ExploreViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        newsAdapter= NewsAdapter()

        binding = FragmentExploreBinding.inflate(layoutInflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareNewsRecyclerView()
        filterListener()

    }

    private fun prepareNewsRecyclerView() {
        binding.rvNewsList.apply {
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter=newsAdapter
        }
    }


    private fun filterListener(){
        binding.ivFilterMenu.setOnClickListener {
            FilterDialogFragment().show(childFragmentManager, "FilterDialogFragment")
        }

    }



}