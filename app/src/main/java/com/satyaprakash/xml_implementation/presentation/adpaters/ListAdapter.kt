package com.satyaprakash.xml_implementation.presentation.adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satyaprakash.xml_implementation.data.model.ListItems
import com.satyaprakash.xml_implementation.databinding.ItemViewLayoutBinding

class ListAdapter(private var list:List<ListItems>):
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var filteredItems = list


   inner class ListViewHolder(val binding :ItemViewLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val binding:ItemViewLayoutBinding=ItemViewLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return  ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        holder.binding.title.text=list[position].title
        holder.binding.imageView2.setImageResource(list[position].image)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun filter(newList:List<ListItems>) {
        list=newList
        notifyDataSetChanged()
    }
}