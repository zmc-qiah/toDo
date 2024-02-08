package com.debug.myapplication.MyListener

import com.debug.myapplication.util.MyType

interface OnClickItem {
    fun onClick(position: Int, entity: MyType)
    fun onClick(position: Int, entity: MyType, flag: Boolean)
}
