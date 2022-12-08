package com.bagaspardanailham.pokedexapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagaspardanailham.pokedexapp.data.remote.response.MovesItem
import com.bagaspardanailham.pokedexapp.databinding.ItemRowPokeTypeBinding

class ListPokeMovesAdapter: ListAdapter<MovesItem, ListPokeMovesAdapter.ListPokeMovesVH>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokeMovesVH {
        val binding = ItemRowPokeTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPokeMovesVH(binding)
    }

    override fun onBindViewHolder(holder: ListPokeMovesVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ListPokeMovesVH(private val binding: ItemRowPokeTypeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovesItem) {
            with(binding) {
                tvTypeItem.text = data.move?.name
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovesItem> =
            object : DiffUtil.ItemCallback<MovesItem>() {
                override fun areItemsTheSame(oldItem: MovesItem, newItem: MovesItem): Boolean {
                    return oldItem.move?.name == newItem.move?.name
                }

                override fun areContentsTheSame(oldItem: MovesItem, newItem: MovesItem): Boolean {
                    return oldItem == newItem
                }

            }
    }

}