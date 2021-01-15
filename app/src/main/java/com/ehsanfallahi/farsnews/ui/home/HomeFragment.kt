package com.ehsanfallahi.farsnews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.ehsanfallahi.farsnews.R
import com.ehsanfallahi.farsnews.databinding.FragmentHomeBinding
import com.ehsanfallahi.farsnews.util.Constant.Companion.MY_TAG
import com.ehsanfallahi.farsnews.util.NewsRecyclerViewAdapter
import com.ehsanfallahi.farsnews.util.ScopedFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeFragment : ScopedFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        val binding=
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        setHasOptionsMenu(true)

        binding.cvChosenNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }

        bindUI()


        return binding.root
    }




    private fun bindUI()=launch {
        Log.i(MY_TAG,"home_Fr_bind UI")
        val news=homeViewModel.getAllNews.await()
        Log.i(MY_TAG,"home_Fr_bind UI_news")
        news.observe(viewLifecycleOwner, Observer {
            if(it==null)return@Observer
            txt_cv_chosen_news.text=it.items[2].title
            Glide.with(this@HomeFragment).load(it.items[2].enclosure.link).into(img_chosen_news)
        })

    }
}