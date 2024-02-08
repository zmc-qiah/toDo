package com.debug.myapplication.viewHolder

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.debug.myapplication.MyListener.OnClickItem
import com.debug.myapplication.adapter.DaysAdapter
import com.debug.myapplication.bean.Calendar
import com.debug.myapplication.bean.Day
import com.debug.myapplication.bean.Info
import com.debug.myapplication.bean.Week
import com.debug.myapplication.databinding.ItemCalendarBinding
import com.debug.myapplication.util.MyType
import com.debug.myapplication.util.getWeekInt
import org.jxxy.debug.corekit.recyclerview.MultipleType
import org.jxxy.debug.corekit.recyclerview.MultipleViewHolder2

class CalendarVH(view: ItemCalendarBinding) : MultipleViewHolder2<ItemCalendarBinding, Calendar>(view) {
    private val spanSizeLookup by lazy {
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = when (adapter.getItemViewType(position)) {
                MyType.INFO -> 7
                else -> 1
            }
        }
    }
    private val TAG = CalendarVH::class.java.simpleName

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setHolder(entity: Calendar) {
        view.dateRv.adapter = adapter
        val manager = view.dateRv.layoutManager as GridLayoutManager
        manager.spanSizeLookup = spanSizeLookup
        setData(entity)
        Log.d(TAG, "setHolder: " + entity.firstDay.date.month)
    }
    private val adapter: DaysAdapter by lazy {
        DaysAdapter(ClickListener)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setData(entity: Calendar) {
        val list = ArrayList<MultipleType>()
        list.add(Week("日"))
        list.add(Week("一"))
        list.add(Week("二"))
        list.add(Week("三"))
        list.add(Week("四"))
        list.add(Week("五"))
        list.add(Week("六"))
        val first = entity.firstDay.date
        val last = first.plusMonths(1L).minusDays(1L)
        val weekInt = getWeekInt(first)
        for (i in 0 until weekInt) {
            list.add(Day(first.minusDays((weekInt - i).toLong()), Day.GREY))
        }
        for (i in first.dayOfMonth until last.dayOfMonth + 1) {
            list.add(Day(first.plusDays((i - 1).toLong())))
        }
        val i1 = getWeekInt(last)
        for (i in i1 until 7) {
            list.add(Day(first.plusDays((i - i1).toLong()), Day.GREY))
        }
        adapter.clearAndAdd(list)
    }
    private val ClickListener by lazy {
        object : OnClickItem {
            override fun onClick(position: Int, entity: MyType) {
            }
            override fun onClick(position: Int, entity: MyType, flag: Boolean) {
                when (entity.type) {
                    MyType.DAY -> {
                        if (flag) {
                            adapter.remove(adapter.InfoIndex)
                        } else {
                            if (adapter.InfoFlag) {
                                adapter.remove(adapter.InfoIndex)
                            }
                            val index = (position / 7 + 1) * 7
                            adapter.insert(index, Info())
                            adapter.InfoFlag = true
                            adapter.InfoIndex = index
                        }
                    }
                }
            }
        }
    }
}
