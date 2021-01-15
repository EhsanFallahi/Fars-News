package com.ehsanfallahi.farsnews.ui.detail

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ehsanfallahi.farsnews.R
import com.ehsanfallahi.farsnews.databinding.NewsDetailFragmentBinding
import com.ehsanfallahi.farsnews.model.models.Item
import com.ehsanfallahi.farsnews.util.copyToClipboard
import com.ehsanfallahi.farsnews.util.shareNewsLink
import com.google.android.material.textview.MaterialTextView

class NewsDetailFragment : Fragment() {

    private lateinit var viewModel: NewsDetailViewModel
    private val args:NewsDetailFragmentArgs by navArgs()
    private lateinit var item: Item
    private lateinit var image:ImageView
    private lateinit var txtTitle:MaterialTextView
    private lateinit var txtDesc:MaterialTextView
    private lateinit var shareImg:ImageView
    private lateinit var copyImg:ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(NewsDetailViewModel::class.java)
        item=args.item
        val view=inflater.inflate(R.layout.news_detail_fragment,container,false)
        image=view.findViewById(R.id.img_detail_news)
        shareImg=view.findViewById(R.id.img_share)
        txtTitle=view.findViewById(R.id.txt_detail_news_title)
        txtDesc=view.findViewById(R.id.txt_detail_news_desc)
        copyImg=view.findViewById(R.id.img_copy)
        initUI()

        shareImg.setOnClickListener { shareNewsLink(item.link,this.requireContext()) }
        copyImg.setOnClickListener { copyToClipboard(item.description,this.requireContext()) }

        return  view.rootView
    }

    private fun initUI() {
        Glide.with(this)
            .load(item.enclosure.link)
            .into(image)
       txtTitle.text=item.title
        txtDesc.text=item.description
    }

}