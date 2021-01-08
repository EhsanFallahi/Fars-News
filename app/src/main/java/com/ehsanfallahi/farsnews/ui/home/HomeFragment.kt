package com.ehsanfallahi.farsnews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ehsanfallahi.farsnews.R
import com.ehsanfallahi.farsnews.model.database.NewsDao
import com.ehsanfallahi.farsnews.model.datasource.NewsDataSource
import com.ehsanfallahi.farsnews.model.repository.NewsRepository
import com.ehsanfallahi.farsnews.model.service.NewsService
import com.ehsanfallahi.farsnews.util.Constant.Companion.MY_TAG
import com.ehsanfallahi.farsnews.util.ScopedFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeFragment : ScopedFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var  textView: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
         textView = root.findViewById(R.id.text_home)
        bindUI()
        return root
    }

    private fun bindUI()=launch {
        val news=homeViewModel.allNews.await()
        news.observe(viewLifecycleOwner, Observer {
            if(it==null)return@Observer
            textView.text=it[2].items[2].title
        })
    }
}