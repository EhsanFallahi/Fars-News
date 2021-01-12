package com.ehsanfallahi.farsnews.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ehsanfallahi.farsnews.model.models.Root
import com.ehsanfallahi.farsnews.model.repository.NewsRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject
constructor(
    private val newsRepository: NewsRepository
    ): ViewModel() {

    fun allNews()=viewModelScope.launch {
        newsRepository.getNews()
    }
  }
