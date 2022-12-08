package com.bagaspardanailham.pokedexapp.ui.detail

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagaspardanailham.pokedexapp.R
import com.bagaspardanailham.pokedexapp.databinding.ActivityPokeDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class PokeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokeDetailBinding
    private val args: PokeDetailActivityArgs by navArgs()

    private val pokeDetailViewModel by viewModels<PokeDetailViewModel>()
    private lateinit var pokeStatsAdapter: ListPokeStatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokeStatsAdapter = ListPokeStatsAdapter()

        setActionBar()
        setContent()
        setAction()
        setupTabLayoutWithViewPager()
    }

    private fun setContent() {
        val pokeResult = args.pokemonResult
        val pokeImg = args.pokeImg
        val dominantColor = args.dominantColor

        Glide.with(this)
            .load(pokeImg)
            .into(binding.tvImgDetail)
        supportActionBar?.title = pokeResult.name
        binding.bgDetail.setBackgroundColor(dominantColor)

    }

    private fun setAction() {
        binding.btnPokeCatch.setOnClickListener {
            val randomNumber = Random.nextInt(0, 100)
            val loading = LoadingDialog(this)
            loading.startLoading()
            lifecycleScope.launch {
                delay(5000)
                loading.isDismiss()
                if (randomNumber > 50) {
                    BottomSheetFragment(args.pokemonResult.name, args.pokeImg, args.dominantColor).show(supportFragmentManager, BottomSheetFragment::class.java.simpleName)
                } else {
                    Toast.makeText(this@PokeDetailActivity, "Try Again", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupTabLayoutWithViewPager() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val bundle = Bundle()
        bundle.putString(EXTRA_NAME, args.pokemonResult.name)
        viewPager.adapter = PokeDetailSectionPagerAdapter(this, bundle)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun setActionBar() {
        setSupportActionBar(binding.customToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        val TAB_TITLES = intArrayOf(
            R.string.tab_text_info,
            R.string.tab_text_stats
        )
        const val EXTRA_NAME = "extra_name"
    }
}