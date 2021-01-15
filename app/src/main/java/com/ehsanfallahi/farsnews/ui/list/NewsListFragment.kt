package com.ehsanfallahi.farsnews.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehsanfallahi.farsnews.R
import com.ehsanfallahi.farsnews.databinding.ListNewsLayoutBinding
import com.ehsanfallahi.farsnews.databinding.NewsListFragmentBinding
import com.ehsanfallahi.farsnews.model.models.Item
import com.ehsanfallahi.farsnews.util.Constant.Companion.MY_TAG
import com.ehsanfallahi.farsnews.util.NewsRecyclerViewAdapter
import com.ehsanfallahi.farsnews.util.ScopedFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_list_fragment.*
import kotlinx.coroutines.launch
@AndroidEntryPoint
class NewsListFragment : ScopedFragment() {

    private lateinit var viewModel: NewsListViewModel
    private lateinit var binding:NewsListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        binding=DataBindingUtil.inflate(inflater,R.layout.news_list_fragment,container,false)
        setUpNewsRecyclerView()

        return binding.root
    }

    private fun setUpNewsRecyclerView() =launch {
        binding.rvListNews.apply {
            layoutManager=LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false)
           val news= viewModel.getAllNews.await()
            news.observe(viewLifecycleOwner, Observer {
                binding.progress.visibility=GONE
                if(it==null) return@Observer
                binding.rvListNews.adapter=NewsRecyclerViewAdapter(it.items)
            })
        }
    }


}