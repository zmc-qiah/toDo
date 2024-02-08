package com.debug.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import com.debug.myapplication.activity.AddActivity
import com.debug.myapplication.adapter.LoopAdapter
import com.debug.myapplication.bean.Calendar
import com.debug.myapplication.bean.Day
import com.debug.myapplication.databinding.ActivityMainBinding
import org.jxxy.debug.corekit.common.BaseActivity
import org.jxxy.debug.corekit.util.load
import org.jxxy.debug.corekit.util.singleClick
import org.jxxy.debug.corekit.util.startActivity
import java.time.LocalDate

// 你要是自己写activity运行不了，记得去AndroidManifest.xml看看有没有添加进去
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val TAG = "MainActivity"

    private val adapter by lazy {
        LoopAdapter()
    }
    override fun bindLayout(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        view.userIv.load("https://i0.hdslb.com/bfs/face/13cd58ffeea63949848a86f4f3abf40b7416ba40.jpg@240w_240h_1c_1s_!web-avatar-nav.webp", true)
        view.dateRv.adapter = adapter
        val now = LocalDate.now()
        val year = now.year
        for (i in 1 until 13) {
            adapter.add(
                Calendar(
                    Day(
                        LocalDate.of(year, i, 1)
                    )
                )
            )
        }
        val page = Int.MAX_VALUE / 2 / 12 * 12 + now.month.value - 1
        view.dateRv.setCurrentItem(page, false)
        view.addBtn.singleClick {
            startActivity<AddActivity>()
        }
    }

    override fun subscribeUi() {
    }
}
