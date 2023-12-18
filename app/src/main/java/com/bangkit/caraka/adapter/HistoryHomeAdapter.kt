package com.bangkit.caraka.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.data.networking.response.HistoryResponse
import com.bangkit.caraka.databinding.ItemHistoryBinding
import com.bumptech.glide.Glide

class HistoryHomeAdapter : RecyclerView.Adapter<HistoryHomeAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Kamus) {
            binding.tvItemName.text = data.latin
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Kamus>() {
        override fun areItemsTheSame(oldItem: Kamus, newItem: Kamus): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Kamus,
            newItem: Kamus
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }

    var onClick: ((Kamus) -> Unit)? = null
}