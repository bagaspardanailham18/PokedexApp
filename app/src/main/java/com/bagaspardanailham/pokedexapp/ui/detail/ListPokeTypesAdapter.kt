package com.bagaspardanailham.pokedexapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagaspardanailham.pokedexapp.data.remote.response.TypesItem
import com.bagaspardanailham.pokedexapp.databinding.ItemRowPokeTypeBinding

class ListPokeTypesAdapter: ListAdapter<TypesItem, ListPokeTypesAdapter.ListPokeTypeVH>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokeTypeVH {
        val binding = ItemRowPokeTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPokeTypeVH(binding)
    }

    override fun onBindViewHolder(holder: ListPokeTypeVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ListPokeTypeVH(private val binding: ItemRowPokeTypeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(types: TypesItem) {
            with(binding) {
                tvTypeItem.text = types.type?.name
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TypesItem> =
            object : DiffUtil.ItemCallback<TypesItem>() {
                override fun areItemsTheSame(oldItem: TypesItem, newItem: TypesItem): Boolean {
                    return oldItem.type?.name == newItem.type?.name
                }

                override fun areContentsTheSame(oldItem: TypesItem, newItem: TypesItem): Boolean {
                    return oldItem == newItem
                }

            }
    }
}