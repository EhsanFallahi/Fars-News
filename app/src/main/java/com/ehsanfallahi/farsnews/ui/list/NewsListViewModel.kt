package com.ehsanfallahi.farsnews.ui.list

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehsanfallahi.farsnews.model.models.Item
import com.ehsanfallahi.farsnews.model.repository.NewsRepository
import com.ehsanfallahi.farsnews.util.Constant
import com.ehsanfallahi.farsnews.util.NewsRecyclerViewAdapter
import com.ehsanfallahi.farsnews.util.lazyDeferred

class NewsListViewModel @ViewModelInject
constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    lateinit var itemData:MutableLiveData<Item>
    lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter
//    init {
//        itemData= MutableLiveData()
//        newsRecyclerViewAdapter= NewsRecyclerViewAdapter()
//    }

    fun getNewsRecyclerAdapter(): NewsRecyclerViewAdapter {
        Log.i(Constant.MY_TAG,"getNewsRecyclerAdapter")
        return newsRecyclerViewAdapter
    }

//    fun setItemData(item:ArrayList<Item>){
//
////        newsRecyclerViewAdapter.setDataItem(item)
//        newsRecyclerViewAdapter.notifyDataSetChanged()
//        Log.i(Constant.MY_TAG,"setItemData:${item.toString()}")
//
//    }

    fun getItemDataObserver():MutableLiveData<Item>{
        return itemData
        Log.i(Constant.MY_TAG,"getItemDataObserver()")
    }

    val getAllNews by lazyDeferred {
        newsRepository.getNews()
    }
}