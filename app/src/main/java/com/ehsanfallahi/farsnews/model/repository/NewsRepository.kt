package com.ehsanfallahi.farsnews.model.repository

import androidx.lifecycle.LiveData
import com.ehsanfallahi.farsnews.model.database.NewsDao
import com.ehsanfallahi.farsnews.model.datasource.NewsDataSource
import com.ehsanfallahi.farsnews.model.models.Root
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsRepository(
    private val newsDataSource: NewsDataSource,
    private val newsDao: NewsDao
) {
    init {
        newsDataSource.getNews.observeForever{newsDownloaded->seveGetNews(newsDownloaded)}
    }

    suspend fun getNews():LiveData<List<Root>>{
        return withContext(Dispatchers.IO){
            initNewsData()
            return@withContext newsDao.getAllNews()
        }

    }

    private fun seveGetNews(newsDownloaded: Root?) {
        GlobalScope.launch(Dispatchers.IO) {
            newsDao.insertChosenNew(newsDownloaded!!)
        }
    }

    private suspend fun initNewsData(){
        if(isFetchNews()){
            fetchNews()
        }
    }

    private suspend fun fetchNews(){
        newsDataSource.getAllNews()
    }

    private fun isFetchNews():Boolean{
        return true
    }
}