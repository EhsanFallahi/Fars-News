package com.ehsanfallahi.farsnews.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ehsanfallahi.farsnews.R
import com.ehsanfallahi.farsnews.databinding.ListNewsLayoutBinding
import com.ehsanfallahi.farsnews.model.models.Item
import com.ehsanfallahi.farsnews.ui.list.NewsListFragmentDirections

class NewsRecyclerViewAdapter(private val items:List<Item>):
    RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>(),Filterable {
    private lateinit var binding:ListNewsLayoutBinding
    var titleListNews=ArrayList<Item>()
    init {
        titleListNews= items as ArrayList<Item>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        binding=DataBindingUtil.inflate(layoutInflater,R.layout.list_news_layout,parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

//        if(items[position].enclosure.link?.isNullOrBlank()!!) {
//            Glide.with(holder.itemView.context)
//                .load(R.drawable.ic_check)
//                .into(binding.imgListNews)
//
//            holder.bind(items[position], "Null")
//        }else{
            holder.bind(items[position], items[position].enclosure.link)
//        }


        holder.itemView.setOnClickListener {
            val direction=NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment2(items[position])
            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount()=items.size

class NewsViewHolder(val binding:ListNewsLayoutBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(items: Item, url:String?){
        binding.txtDetailNewsTitle.text=items.title
        binding.txtDetailNewsDesc.text=items.description

            Glide.with(itemView.context).load(url)

                .placeholder(R.drawable.ic_check)
                .into(binding.imgListNews)
            binding.executePendingBindings()
    }
    }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charSearch=charSequence.toString()
                if (charSearch.isEmpty()){
                    titleListNews= items as ArrayList<Item>
                }else{
                    val resultList=ArrayList<Item>()
                    for (row in items) {
                        if (row.title.toLowerCase().contains(charSequence.toString().toLowerCase())||
                                row.description.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    titleListNews = resultList
                }
                val filterResults = FilterResults()
                filterResults.values=titleListNews
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                titleListNews = results?.values as ArrayList<Item>
                notifyDataSetChanged()
            }

        }
    }
}
