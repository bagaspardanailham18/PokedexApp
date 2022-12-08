package com.bagaspardanailham.pokedexapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagaspardanailham.pokedexapp.data.remote.response.StatsItem
import com.bagaspardanailham.pokedexapp.databinding.ItemRowPokeStatBinding

class ListPokeStatsAdapter: ListAdapter<StatsItem, ListPokeStatsAdapter.ListPokeStatsVH>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokeStatsVH {
        val binding = ItemRowPokeStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPokeStatsVH(binding)
    }

    override fun onBindViewHolder(holder: ListPokeStatsVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ListPokeStatsVH(private val binding: ItemRowPokeStatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: StatsItem) {
            with(binding) {
                hpStat.setmDefText(data.stat?.name.toString())
                hpStat.setmValueText(data.baseStat.toString())
                data.baseStat?.let { hpStat.setmPercentage(it) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<StatsItem> =
            object : DiffUtil.ItemCallback<StatsItem>() {
                override fun areItemsTheSame(oldItem: StatsItem, newItem: StatsItem): Boolean {
                    return oldItem.baseStat == newItem.baseStat
                }

                override fun areContentsTheSame(oldItem: StatsItem, newItem: StatsItem): Boolean {
                    return oldItem == newItem
                }

            }
    }
}