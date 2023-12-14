package com.bangkit.caraka.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bangkit.caraka.data.networking.response.ListStoryItem
import com.bangkit.caraka.databinding.ItemHistoryBinding

class HistoryAdapter(private val listItem: List<ListStoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListStoryItem) {
            binding.apply {
                ivItemPhoto.load(item.photoUrl)
                tvItemCategory.text = item.id
                tvItemName.text = item.name
                tvItemSubname.text = item.name
//                itemView.setOnClickListener {
//                    val intentDetail = Intent(itemView.context, DetailActivity::class.java)
//                    intentDetail.putExtra(DetailActivity.EXTRA_USERNAME, item.username)
//                    itemView.context.startActivity(intentDetail)
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listItem[position])
    }
}