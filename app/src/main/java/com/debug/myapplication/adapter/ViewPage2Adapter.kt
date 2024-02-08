package com.debug.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPage2Adapter(
    activity: FragmentActivity,
    val fragment: List<Fragment>,
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = fragment.size
    override fun createFragment(position: Int): Fragment = fragment[position]
}