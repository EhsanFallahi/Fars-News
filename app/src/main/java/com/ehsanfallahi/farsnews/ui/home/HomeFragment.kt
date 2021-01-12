package com.ehsanfallahi.farsnews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
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

//        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.allNews()

        setHasOptionsMenu(true)

        return root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)

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