package com.ehsanfallahi.farsnews.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ehsanfallahi.farsnews.R
import com.ehsanfallahi.farsnews.databinding.ListNewsLayoutBinding
import com.ehsanfallahi.farsnews.model.models.Item
import com.ehsanfallahi.farsnews.ui.list.NewsListFragmentDirections

class NewsRecyclerViewAdapter(private val item:List<Item>):RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding:ListNewsLayoutBinding=DataBindingUtil.inflate(layoutInflater,R.layout.list_news_layout,parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(item[position],item[position].enclosure.link)

        holder.itemView.setOnClickListener {
            val direction=NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment2(item[position])
            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount()=item.size

class NewsViewHolder(val binding:ListNewsLayoutBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(item: Item,url:String){
        binding.txtDetailNewsTitle.text=item.title
        binding.txtDetailNewsDesc.text=item.description
        Glide.with(itemView.context).load(url).into(binding.imgListNews)
        binding.executePendingBindings()
    }
    }
}

//    companion object{
//        @BindingAdapter("loadImage")
//        fun loadImage(image:ImageView,url:String){
//            Glide.with(image)
//                .load(url)
//                .placeholder(R.drawable.ic_check)
//                .error(R.drawable.ic_launcher_foreground)
//                .into(image)
//        }
//    }
//}