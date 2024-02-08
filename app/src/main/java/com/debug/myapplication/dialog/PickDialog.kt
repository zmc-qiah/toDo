package com.debug.myapplication.dialog

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.debug.myapplication.MyListener.OnClickPickDialog
import com.debug.myapplication.bean.PickDialogBean
import com.debug.myapplication.databinding.DialogPickBinding
import enen
import org.jxxy.debug.corekit.common.BaseDialog
import org.jxxy.debug.corekit.util.singleClick

class PickDialog(val listener: OnClickPickDialog, val bean: PickDialogBean) : BaseDialog<DialogPickBinding>() {
    override fun getView(inflater: LayoutInflater, parent: ViewGroup?): DialogPickBinding {
        return DialogPickBinding.inflate(inflater, parent, false)
    }
    init {
        gravity = Gravity.BOTTOM
    }

    override fun initView(view: DialogPickBinding) {
        view.describeTV.text = bean.describe
        view.numPick.apply {
            displayedValues = bean.value1
            minValue = bean.arr1[0]
            maxValue = bean.arr1[1]
            value = bean.arr1[2]
            setOnValueChangedListener { picker, oldVal, newVal ->
                enen()
            }
        }
        view.unitPick.apply {
            displayedValues = bean.value2
            minValue = bean.arr2[0]
            maxValue = bean.arr2[1]
            value = bean.arr2[2]
            setOnValueChangedListener { picker, oldVal, newVal ->
                enen()
            }
        }
        view.cancelBtn.singleClick {
            listener.onCancel()
        }
        view.submitBtn.singleClick {
            listener.onSubmit(
                view.numPick.value,
                view.unitPick.value
            )
        }
    }
}
