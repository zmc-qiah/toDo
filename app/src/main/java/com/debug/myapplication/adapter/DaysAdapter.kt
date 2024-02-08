package com.debug.myapplication.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.debug.myapplication.MyListener.OnClickItem
import com.debug.myapplication.R
import com.debug.myapplication.bean.Day
import com.debug.myapplication.bean.Week
import com.debug.myapplication.databinding.ItemDayBinding
import com.debug.myapplication.databinding.ItemInfoBinding
import com.debug.myapplication.databinding.ItemWeekBinding
import com.debug.myapplication.util.MyType
import com.debug.myapplication.viewHolder.InfoViewHolder
import org.jxxy.debug.corekit.recyclerview.MultipleViewHolder2
import org.jxxy.debug.corekit.util.ResourceUtil
import org.jxxy.debug.corekit.util.singleClick

class DaysAdapter(val listener: OnClickItem) : InsertMultipleTypeAdapter() {
    var InfoFlag = false
    var InfoIndex = -1
    override fun createViewHolder(
        i: Int,
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup
    ): RecyclerView.ViewHolder? = when (i) {
        MyType.WEEK -> {
            object : MultipleViewHolder2<ItemWeekBinding, Week>(ItemWeekBinding.inflate(layoutInflater, viewGroup, false)) {
                override fun setHolder(entity: Week) {
                    view.weekTv.text = entity.data
                }
            }
        }
        MyType.DAY -> {
            object : MultipleViewHolder2<ItemDayBinding, Day>(ItemDayBinding.inflate(layoutInflater, viewGroup, false)) {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun setHolder(entity: Day) {
                    when (entity.flag) {
                        Day.BLACK -> {
                            view.dayIv.setTextColor(ResourceUtil.getColor(R.color.black))
                        }
                        Day.GREEN -> {
                            view.dayIv.setTextColor(ResourceUtil.getColor(org.jxxy.debug.corekit.R.color.color_green))
                        }
                        Day.GREY -> {
                            view.dayIv.setTextColor(ResourceUtil.getColor(org.jxxy.debug.corekit.R.color.grey))
                        }
                    }
                    view.dayIv.text = entity.date.dayOfMonth.toString()
                    view.dayIv.tag = false
                    view.root.singleClick {
                        val b = view.dayIv.tag as Boolean
                        listener.onClick(adapterPosition, entity, b)
                        view.dayIv.tag = !b
                        if (!b) {
                            view.dayIv.setTextColor(ResourceUtil.getColor(R.color.light_blue_600))
                        } else {
                            when (entity.flag) {
                                Day.BLACK -> {
                                    view.dayIv.setTextColor(ResourceUtil.getColor(R.color.black))
                                }
                                Day.GREEN -> {
                                    view.dayIv.setTextColor(ResourceUtil.getColor(org.jxxy.debug.corekit.R.color.color_green))
                                }
                                Day.GREY -> {
                                    view.dayIv.setTextColor(ResourceUtil.getColor(org.jxxy.debug.corekit.R.color.grey))
                                }
                            }
                        }
                    }
                }
            }
        }
        MyType.INFO -> {
            InfoViewHolder(ItemInfoBinding.inflate(layoutInflater, viewGroup, false))
        }
        else -> null
    }
}
