package com.ehsanfallahi.farsnews.model.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ehsanfallahi.farsnews.model.database.NewsDao
import com.ehsanfallahi.farsnews.model.models.Root
import com.ehsanfallahi.farsnews.model.service.NewsService
import com.ehsanfallahi.farsnews.util.Constant.Companion.MY_TAG
import com.ehsanfallahi.farsnews.util.NoConnectivityException

class NewsDataSource(private val newsService: NewsService) {

     private val _getNews= MutableLiveData<Root>()
     val getNews:LiveData<Root>
     get() = _getNews

    suspend fun getAllNews(){
        try{
            val response=newsService.getAllNews().await()
            _getNews.postValue(response)
        }catch (e:NoConnectivityException){
            Log.e(MY_TAG,"No internet connection:${e.message}")
        }
    }


}