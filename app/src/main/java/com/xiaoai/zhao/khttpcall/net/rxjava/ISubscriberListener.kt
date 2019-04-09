package com.xiaoai.zhao.khttpcall.net.rxjava

interface ISubscriberListener<T> {
    fun onSuccess(t:T)
    fun onError(errorInfo:String)
}