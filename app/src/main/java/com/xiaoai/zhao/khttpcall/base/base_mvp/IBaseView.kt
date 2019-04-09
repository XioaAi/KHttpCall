package com.xiaoai.zhao.khttpcall.base.base_mvp

interface IBaseView {
    fun logd(log:String)
    fun showFailMsg( msg : String)
    fun showErrorPage()
    fun hindErrorPage()
}