package com.xiaoai.zhao.khttpcall.base.base_mvp

import rx.Subscription
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

open class BasePresenter<V : IBaseView ,M : IBaseModule> : IBasePresenter{

    var baseView: V
    var baseModule: M

    var composteSubscription = CompositeSubscription()

    @Inject
    constructor(baseView: V, baseModule: M) {
        this.baseView = baseView
        this.baseModule = baseModule
    }

    protected fun addSubscription(subscription: Subscription){
        composteSubscription.add(subscription)
    }

    protected fun unSubscribe(){
        if(composteSubscription.hasSubscriptions()){
            composteSubscription.unsubscribe()
        }
    }

    override fun onDestory() {
        unSubscribe()

        if(baseModule!=null){
            baseModule.onDestory()
        }

        baseModule == null
        baseView == null

    }

}