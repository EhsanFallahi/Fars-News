package com.ehsanfallahi.farsnews.model.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ehsanfallahi.farsnews.model.models.Root
import com.ehsanfallahi.farsnews.model.service.NewsService
import com.ehsanfallahi.farsnews.util.Constant.Companion.MY_TAG
import com.ehsanfallahi.farsnews.util.NoConnectivityException
import javax.inject.Inject

class NewsDataSource
@Inject
constructor(private val newsService: NewsService) {

     private val _getAllNews= MutableLiveData<Root>()
     val getAllNews:LiveData<Root>
     get() = _getAllNews

    suspend fun getAllNews(){
        try{
            val response=newsService.getAllNews().await()
            _getAllNews.postValue(response)
        }catch (e:NoConnectivityException){
            Log.e(MY_TAG,"No internet connection:${e.message}")
        }
    }

}