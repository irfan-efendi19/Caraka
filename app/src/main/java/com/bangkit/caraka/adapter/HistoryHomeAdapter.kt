package com.bangkit.caraka.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.caraka.R
import com.bangkit.caraka.data.database.Artikel
import com.bangkit.caraka.databinding.ItemHistoryBinding
import com.bangkit.caraka.ui.detailhistory.DetailHistoryActivity
import com.bumptech.glide.Glide

class HistoryHomeAdapter(private val artikel: List<Artikel>) :
    RecyclerView.Adapter<HistoryHomeAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artikel) {
            binding.apply {
                tvItemName.text = item.judul
                Glide.with(this.ivItemPhoto)
                    .load(item.img_sejarah)
                    .fitCenter()
                    .into(binding.ivItemPhoto);
                binding.cardViewHistory.setOnClickListener {
                    val intent = Intent(itemView.context, DetailHistoryActivity::class.java)
                    intent.putExtra(DetailHistoryActivity.JUDULARTIKEL, item.judul)
                    intent.putExtra(DetailHistoryActivity.THUMNAIL, item.img_sejarah)
                    intent.putExtra(DetailHistoryActivity.ARTIKEL, item.desc)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val artikel = artikel[position]
        holder.bind(artikel)
    }

    override fun getItemCount(): Int {
        return minOf(artikel.size, 3)
    }
}