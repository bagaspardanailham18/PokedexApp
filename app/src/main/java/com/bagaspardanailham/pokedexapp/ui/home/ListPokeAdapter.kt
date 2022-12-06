package com.bagaspardanailham.pokedexapp.ui.home

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagaspardanailham.pokedexapp.R
import com.bagaspardanailham.pokedexapp.data.remote.response.ListPokemonResponse
import com.bagaspardanailham.pokedexapp.data.remote.response.ResultsItem
import com.bagaspardanailham.pokedexapp.databinding.ItemRowPokemonBinding
import com.bagaspardanailham.pokedexapp.utils.getPictUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class ListPokeAdapter: ListAdapter<ResultsItem, ListPokeAdapter.ListPokeVH>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokeVH {
        val binding = ItemRowPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPokeVH(binding)
    }

    override fun onBindViewHolder(holder: ListPokeVH, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class ListPokeVH(private val binding: ItemRowPokemonBinding): RecyclerView.ViewHolder(binding.root) {

        var dominantColor: Int = 0
        var picture: String? = ""

        fun bind(data: ResultsItem) {
            with(binding) {
                tvNameItem.text = data.name
                loadImage(this, data)
            }
        }

        private fun loadImage(binding: ItemRowPokemonBinding, pokemonResult: ResultsItem) {
            picture = pokemonResult.url?.getPictUrl()

            binding.apply {
                Glide.with(root)
                    .load(picture)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressCircular.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            val drawable = resource as BitmapDrawable
                            val bitmap = drawable.bitmap
                            Palette.Builder(bitmap).generate {
                                it?.let { palette ->
                                    dominantColor = palette.getDominantColor(
                                        ContextCompat.getColor(root.context, R.color.white)
                                    )
                                    itemPokeCard.setCardBackgroundColor(dominantColor)
                                }
                            }
                            progressCircular.isVisible = false
                            return false
                        }
                    })
                    .into(tvImgItem)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ResultsItem> =
        object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(
                oldItem: ResultsItem,
                newItem: ResultsItem
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: ResultsItem,
                newItem: ResultsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}