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

        bindUI()

        return binding.root
    }




    private fun bindUI()=launch {
        binding.cvChosenNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvOneNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvTwoNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvThreeNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvFourNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvFiveNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvSixNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvSevenNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvEightNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvNineNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        binding.cvTenNews.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_newsListFragment)
        }
        val news=homeViewModel.getAllNews.await()
        news.observe(viewLifecycleOwner, Observer {
            if(it==null)return@Observer
            txt_cv_chosen_news.text=it.items[2].title
            Glide.with(this@HomeFragment).load(it.items[0].enclosure.link).into(img_chosen_news)
            Glide.with(this@HomeFragment).load(it.items[1].enclosure.link).into(img_one_news)
            Glide.with(this@HomeFragment).load(it.items[2].enclosure.link).into(img_two_news)
            Glide.with(this@HomeFragment).load(it.items[3].enclosure.link).into(img_three_news)
            Glide.with(this@HomeFragment).load(it.items[4].enclosure.link).into(img_four_news)
            Glide.with(this@HomeFragment).load(it.items[5].enclosure.link).into(img_five_news)
            Glide.with(this@HomeFragment).load(it.items[6].enclosure.link).into(img_six_news)
            Glide.with(this@HomeFragment).load(it.items[7].enclosure.link).into(img_seven_news)
            Glide.with(this@HomeFragment).load(it.items[8].enclosure.link).into(img_eight_news)
            Glide.with(this@HomeFragment).load(it.items[9].enclosure.link).into(img_nine_news)
            Glide.with(this@HomeFragment).load(it.items[10].enclosure.link).into(img_ten_news)

        })

    }
}