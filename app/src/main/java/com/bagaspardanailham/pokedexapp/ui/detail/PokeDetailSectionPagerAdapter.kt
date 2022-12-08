package com.bagaspardanailham.pokedexapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PokeDetailSectionPagerAdapter(activity: AppCompatActivity, data: Bundle): FragmentStateAdapter(activity) {

    private var fragmentBundle: Bundle = data

    override fun getItemCount(): Int {
        return PokeDetailActivity.TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = PokeInfoFragment()
            else -> fragment = PokeStatsFragment()
        }

        fragment.arguments = this.fragmentBundle

        return fragment as Fragment
    }
}