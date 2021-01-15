package com.ehsanfallahi.farsnews.ui.home

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.ehsanfallahi.farsnews.model.models.Item
import com.ehsanfallahi.farsnews.model.models.Root
import com.ehsanfallahi.farsnews.model.repository.NewsRepository
import com.ehsanfallahi.farsnews.util.Constant
import com.ehsanfallahi.farsnews.util.NewsRecyclerViewAdapter
import com.ehsanfallahi.farsnews.util.lazyDeferred
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject
constructor(
    private val newsRepository: NewsRepository
    ): ViewModel() {

    val getAllNews by lazyDeferred {
        Log.i(Constant.MY_TAG,"home_VM_lazyDeferred")
        newsRepository.getNews()
    }
  }
