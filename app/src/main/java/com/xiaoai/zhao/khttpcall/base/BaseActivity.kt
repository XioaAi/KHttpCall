package com.xiaoai.zhao.khttpcall.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import butterknife.ButterKnife
import com.xiaoai.zhao.khttpcall.base.base_mvp.BasePresenter
import com.xiaoai.zhao.khttpcall.base.base_mvp.IBaseModule
import com.xiaoai.zhao.khttpcall.base.base_mvp.IBaseView
import com.xiaoai.zhao.khttpcall.toast
import javax.inject.Inject

open class BaseMVPActivity<V : IBaseView ,M : IBaseModule,P : BasePresenter<V,M>> : AppCompatActivity(), IBaseView {
    override fun logd(log: String) {
        Log.i("XiaoAi",log?:"")
    }

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
    }


    override fun showFailMsg(msg: String) {
        toast(msg)
    }

    override fun showErrorPage() {

    }

    override fun hindErrorPage() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestory()
    }

}
