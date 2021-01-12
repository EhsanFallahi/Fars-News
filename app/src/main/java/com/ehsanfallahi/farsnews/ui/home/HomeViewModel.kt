package com.ehsanfallahi.farsnews.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ehsanfallahi.farsnews.model.models.Root
import com.ehsanfallahi.farsnews.model.repository.NewsRepository
import com.ehsanfallahi.farsnews.util.lazyDeferred
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject
constructor(
    private val newsRepository: NewsRepository
    ): ViewModel() {


    fun allNews()=viewModelScope.launch {

//    private val _text = MutableLiveData<List<Root>>()
//    val _text:LiveData<List<Root>>
//    val text:MutableLiveData<List<Root>>
//    get() = _text
//

    val allNews by lazyDeferred {

        newsRepository.getNews()
    }
  }
