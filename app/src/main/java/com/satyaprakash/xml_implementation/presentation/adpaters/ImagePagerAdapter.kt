package com.satyaprakash.xml_implementation.presentation.adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satyaprakash.xml_implementation.databinding.ItemImageBinding

class ImagePagerAdapter(private val image:List<Int>):RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val binding :ItemImageBinding=ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)

       return ImageViewHolder( binding)
    }

    override fun onBindViewHolder(holder: ImagePagerAdapter.ImageViewHolder, position: Int) {
        holder.binding.imageView.setImageResource(image[position])

    }

    override fun getItemCount(): Int {
        return image.size
    }

    inner class ImageViewHolder(val binding: ItemImageBinding):RecyclerView.ViewHolder(binding.root) {

    }

}