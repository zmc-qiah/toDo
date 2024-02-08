package com.debug.myapplication // ktlint-disable filename

import org.jxxy.debug.corekit.common.BaseApplication

// 这个非必要不要动
class myApplication : BaseApplication() {
    companion object {
        private const val ICON_FONT = "iconfont.ttf"

        // 这个BASE_URL是我自己写的后端项目的网址，等林学长的项目上云后再改成他的
        private const val BASE_URL = "http://192.168.1.143:8080/"
    }
    override fun httpBaseUrl(): String = BASE_URL

    override fun iconFontPath(): String = ICON_FONT
}
