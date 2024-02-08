package com.debug.myapplication.weigt

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children

class CenterLayout(context: Context, attributeSet: AttributeSet) : ViewGroup(context) {
    val childBondMap = LinkedHashMap<Int, ArrayList<Rect>>()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var raw = 0
        var col = 0
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var maxWidth = 0
        var maxHeight = 0
        for ((index, child) in children.withIndex()) {
            measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed)
            if (widthUsed + child.measuredWidth > maxWidth) {
                maxWidth = widthUsed + child.measuredWidth
            }
            if (heightUsed + child.measuredHeight > maxHeight) {
                maxHeight = heightUsed + child.measuredHeight
            }
            if (widthUsed + child.measuredWidth < widthSize) {
                heightUsed = maxHeight
                widthUsed = 0
                raw++
                col = 0
                measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed)
            }
            if (childBondMap[raw] == null) {
                childBondMap[raw] = ArrayList()
            }
            if (childBondMap[raw]!!.size <= col) {
                childBondMap[raw]!![col] = Rect()
            }
            childBondMap[raw]!![col].set(widthUsed, heightUsed, widthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
            col++
            widthUsed += child.measuredWidth
        }
        setMeasuredDimension(widthUsed, heightUsed)
    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var col = 0
        var raw = 0
        for ((index, child) in children.withIndex()) {
            val rect = childBondMap[raw]!![col]
            child.layout(rect.left, rect.top, rect.right, rect.bottom)
            if (col > childBondMap[raw]!!.size) {
                col = 0
                raw++
            }
            col++
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}
