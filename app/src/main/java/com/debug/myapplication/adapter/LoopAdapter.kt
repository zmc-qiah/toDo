package com.debug.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.debug.myapplication.databinding.ItemCalendarBinding
import com.debug.myapplication.viewHolder.CalendarVH
import org.jxxy.debug.corekit.recyclerview.MultipleType
import org.jxxy.debug.corekit.recyclerview.MultipleTypeAdapter
import org.jxxy.debug.corekit.recyclerview.ViewHolderTag

class LoopAdapter : MultipleTypeAdapter() {
    override fun createViewHolder(
        viewType: Int,
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder = CalendarVH(ItemCalendarBinding.inflate(inflater, parent, false))

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
    val size
        get() = data.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderTag<MultipleType>).setHolder(data.get(position % data.size))
    }
}
