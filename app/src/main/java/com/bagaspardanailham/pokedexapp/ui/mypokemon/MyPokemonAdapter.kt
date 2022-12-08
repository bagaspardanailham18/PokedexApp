package com.bagaspardanailham.pokedexapp.ui.mypokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagaspardanailham.pokedexapp.data.local.model.MyPokeCollectionEntity
import com.bagaspardanailham.pokedexapp.databinding.ItemRowPokemonBinding
import com.bagaspardanailham.pokedexapp.databinding.ItemRowPokemonCollectionBinding
import com.bumptech.glide.Glide

class MyPokemonAdapter: ListAdapter<MyPokeCollectionEntity, MyPokemonAdapter.MyPokemonVH>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPokemonVH {
        val binding = ItemRowPokemonCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPokemonVH(binding)
    }

    override fun onBindViewHolder(holder: MyPokemonVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class MyPokemonVH(private val binding: ItemRowPokemonCollectionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyPokeCollectionEntity) {
            with(binding) {
                tvNameItem.text = data.name
                tvNicknameItem.text = data.nickname
                Glide.with(itemView.context)
                    .load(data.imgUrl)
                    .into(tvImgItem)

                itemPokeCard.setCardBackgroundColor(data.dominantColor!!)

                itemView.setOnClickListener { onItemClickCallback.onItemClicked(data) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MyPokeCollectionEntity)
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MyPokeCollectionEntity> =
            object : DiffUtil.ItemCallback<MyPokeCollectionEntity>() {
                override fun areItemsTheSame(
                    oldItem: MyPokeCollectionEntity,
                    newItem: MyPokeCollectionEntity
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: MyPokeCollectionEntity,
                    newItem: MyPokeCollectionEntity
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

}