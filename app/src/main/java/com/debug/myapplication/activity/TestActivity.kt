package com.debug.myapplication.activity

import com.debug.myapplication.databinding.ActivityTestBinding
import org.jxxy.debug.corekit.common.BaseActivity

class TestActivity : BaseActivity<ActivityTestBinding>() {
    override fun bindLayout(): ActivityTestBinding = ActivityTestBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun subscribeUi() {
    }
}
