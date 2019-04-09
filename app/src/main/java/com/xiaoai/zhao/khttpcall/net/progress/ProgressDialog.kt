package com.xiaoai.zhao.khttpcall.net.progress

import android.app.Dialog
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import com.xiaoai.zhao.khttpcall.R

class ProgressDialog{

    var mDialog : Dialog
    var isCancle : Boolean = false
    var iProgressDialog:IProgressDialog

    constructor(mContext:Context,
                isCancle : Boolean,
                iProgressDialog:IProgressDialog){
        mDialog = createDialog(mContext)
        this.isCancle = isCancle
        this.iProgressDialog = iProgressDialog

    }

    fun createDialog(context: Context):Dialog{
        if(mDialog == null){
            var layoutInflater : LayoutInflater  = LayoutInflater.from(context)
            var loadingView : View = layoutInflater.inflate(R.layout.net_loading_dialog,null,false)
            mDialog = Dialog(context,R.style.net_loading_dialog)
            mDialog.setContentView(loadingView,ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.FILL_PARENT,ConstraintLayout.LayoutParams.FILL_PARENT))
            if(isCancle && iProgressDialog!=null){
                mDialog.setOnCancelListener{
                    iProgressDialog.cancleProgress()
                }
            }
        }
        return mDialog
    }

    fun showProgress(){
        if(mDialog!=null){
            mDialog.show()
        }
    }

    fun closeProgress(){
        if(mDialog!=null){
            mDialog.dismiss()
        }
    }


}