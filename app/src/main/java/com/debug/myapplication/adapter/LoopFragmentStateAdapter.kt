package com.debug.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class LoopFragmentStateAdapter(private val data: List<Fragment>, val activity: FragmentActivity) : FragmentStateAdapter(activity) {

    val size: Int
        get() = data.size
    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment = data.get(position % size)
}
