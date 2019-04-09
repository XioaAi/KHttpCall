package com.xiaoai.zhao.khttpcall

import android.content.Context
import android.widget.Toast

fun Context.getMainComponent() = App.instance.apiComponent

fun Context.toast (msg:String,length:Int = Toast.LENGTH_LONG){
    Toast.makeText(this,msg,length).show()
}

