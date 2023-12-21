package com.bangkit.caraka.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.data.database.Langganan
import com.bangkit.caraka.databinding.ItemAksaraBinding
import com.bangkit.caraka.databinding.ItemLanggananBinding

class LanggananAdapter(private val langganan: List<Langganan>) :
    RecyclerView.Adapter<LanggananAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemLanggananBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Langganan, position: Int) {
            binding.apply {
                tvDurasibulan.text = item.durasi.toString() + " Bulan"
                tvhargaberlangganan.text = "Rp. " + item.harga.toString()
                binding.cardViewKamus.setOnClickListener {
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemLanggananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val harga = langganan[position]
        holder.bind(harga, position)
    }

    override fun getItemCount(): Int = langganan.size
}