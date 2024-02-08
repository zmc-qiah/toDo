package com.debug.myapplication.activity

import android.view.Gravity
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.debug.myapplication.MyListener.OnClickPickDialog
import com.debug.myapplication.R
import com.debug.myapplication.adapter.ViewPage2Adapter
import com.debug.myapplication.databinding.ActivityAddBinding
import com.debug.myapplication.databinding.HomeTabBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.jxxy.debug.corekit.common.BaseActivity
import org.jxxy.debug.corekit.util.ResourceUtil

class AddActivity : BaseActivity<ActivityAddBinding>() {
    private lateinit var adapter: ViewPage2Adapter
    override fun bindLayout(): ActivityAddBinding = ActivityAddBinding.inflate(layoutInflater)
    private val tabScaleFactor = 1.2f // 放大的比例
    private val tabDefaultScale = 1.0f // 默认比例

    override fun initView() {
        view.describeTv.text = "创建日程"
        adapter = ViewPage2Adapter(this, listOf<Fragment>(Fragment(), Fragment()))
        view.viewpager.adapter = adapter
        val viewpager = view.viewpager
        val tabLayout = view.tableLayout
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            val binding = HomeTabBinding.inflate(layoutInflater, tabLayout, false)
            tab.customView = binding.root
            tab.tag = binding
            binding.tabTV.text = if (position == 0) "日程" else "纪念日"
        }.attach()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tag = tab?.tag
                if (tag is HomeTabBinding) {
                    animateTabView(tag.tabTV, true)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tag = tab?.tag
                if (tag is HomeTabBinding) {
                    animateTabView(tag.tabTV, false)
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        val homeTabBinding = tabLayout.getTabAt(0)?.tag as HomeTabBinding
        animateTabView(homeTabBinding.tabTV, true)

//        PickDialog(
//            listener,
//            PickDialogBean(
//                "选择循环周期",
//                arrayOf<Int>(0, 364, 0),
//                arrayOf<Int>(0, 2, 1),
//                value1 = Array(365) { (it + 1).toString() },
//                value2 = arrayOf("月", "周", "日")
//            )
//        ).show(supportFragmentManager)
    }

    override fun subscribeUi() {
    }
    private fun animateTabView(tabItemView: TextView, isSelected: Boolean) {
        if (tabItemView is TextView) {
            if (isSelected) {
                tabItemView.animate()
                    .scaleX(tabScaleFactor)
                    .scaleY(tabScaleFactor)
                    .setDuration(200)
                    .start()
                tabItemView.gravity = Gravity.CENTER // 设置文本居中
                tabItemView.setTextColor(ResourceUtil.getColor(R.color.blue))
            } else {
                tabItemView.animate()
                    .scaleX(tabDefaultScale)
                    .scaleY(tabDefaultScale)
                    .setDuration(200)
                    .start()
                tabItemView.setPadding(0, 0, 0, 0) // 清除 padding
                tabItemView.gravity = Gravity.CENTER_VERTICAL // 恢复文本的默认位置
                tabItemView.setTextColor(ResourceUtil.getColor(R.color.grey))
            }
        }
    }

    private val listener by lazy {
        object : OnClickPickDialog {
            override fun onSubmit(value1: Int, value2: Int) {
            }

            override fun onCancel() {
            }
        }
    }
}
