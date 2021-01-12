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
import com.ehsanfallahi.farsnews.model.datasource.NewsDataSource
import com.ehsanfallahi.farsnews.model.service.NewsService
import com.ehsanfallahi.farsnews.util.ConnectivityInterceptorImpl
import com.ehsanfallahi.farsnews.util.Constant.Companion.MY_TAG
import kotlinx.coroutines.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val newsService =NewsService(ConnectivityInterceptorImpl(this.requireContext()))
         val data=NewsDataSource(newsService)
        data.getNews.observe(viewLifecycleOwner, Observer {
            textView.text=it.toString()
        })
        Log.i(MY_TAG, "apiService")

        Log.i(MY_TAG, "result")
        GlobalScope.launch(Dispatchers.Main) {

            data.getAllNews()

//            try {
//                val response = apiService.getAllNews().await()
//                val body = response
//                body?.let {
//                    // Handle success or any other stuff
//                    if (it.status == "ok") {
//                        textView.text =body.toString()
//                    }else{
//                        Toast.makeText(context, "error response", Toast.LENGTH_LONG).show()
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.i(MY_TAG, "result")
//            }
        }
        return root
    }
}