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
import com.ehsanfallahi.farsnews.util.ConnectivityInterceptorImpl
import com.ehsanfallahi.farsnews.util.Constant.Companion.MY_TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var newsDao: NewsDao

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.allNews()
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text=it.toString()
//        })

//         val newsService =NewsService(ConnectivityInterceptorImpl(this.requireContext()))
//         val data=NewsDataSource(newsService)
//        val repo=NewsRepository(data,newsDao)
//        data.getNews.observe(viewLifecycleOwner, Observer {
//            textView.text=it.toString()
//        })
//       ?

        Log.i(MY_TAG, "apiService")

        Log.i(MY_TAG, "result")
//        GlobalScope.launch(Dispatchers.Main) {
//            data.getAllNews()
//        }
        return root
    }
}