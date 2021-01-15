package com.ehsanfallahi.farsnews.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ehsanfallahi.farsnews.model.database.NewsDao
import com.ehsanfallahi.farsnews.model.datasource.NewsDataSource
import com.ehsanfallahi.farsnews.model.models.Root
import com.ehsanfallahi.farsnews.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource,
    private val newsDao: NewsDao
) {
    init {
        Log.i(Constant.MY_TAG,"repo_init")
        newsDataSource.getAllNews.observeForever{ newsDownloaded->saveAllNewsToDB(newsDownloaded)}

    }

    suspend fun getNews():LiveData<Root>{
        Log.i(Constant.MY_TAG,"repo_fun getNews()")
        return withContext(Dispatchers.IO){
            initNewsData()
            Log.i(Constant.MY_TAG,"repo_fun getNews()_ initNewsData()")
            return@withContext newsDao.getAllNews()
        }

    }

    private fun saveAllNewsToDB(newsDownloaded: Root?) {
        Log.i(Constant.MY_TAG,"repo_fun seveGetNews()")
        GlobalScope.launch(Dispatchers.IO) {
            Log.i(Constant.MY_TAG,"repo_fun seveGetNews()_GlobalScope")
            newsDao.insertAllNews(newsDownloaded!!)
        }
    }

    private suspend fun initNewsData(){
        if(isFetchNews()){
            fetchNews()
        }
    }

    private suspend fun fetchNews(){
        Log.i(Constant.MY_TAG,"repo_suspend fun fetchNews()")
        newsDataSource.getAllNews()
    }

    private fun isFetchNews():Boolean{
        return true
    }
}