package com.xiaoai.zhao.khttpcall.net.rxjava

import android.content.Context
import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializer
import com.xiaoai.zhao.khttpcall.base.base_entities.BaseResultEntity
import com.xiaoai.zhao.khttpcall.net.APIException
import com.xiaoai.zhao.khttpcall.net.progress.IProgressDialog
import com.xiaoai.zhao.khttpcall.net.progress.ProgressDialog
import org.json.JSONException
import retrofit2.adapter.rxjava.HttpException
import rx.Subscriber
import java.io.InterruptedIOException
import java.io.NotSerializableException
import java.net.SocketException
import java.net.UnknownHostException

class RxSubscriber<T>(iSubscriberListener : ISubscriberListener<T>) : Subscriber<T>() {

    var context:Context ?= null
    var iSubscriberListener : ISubscriberListener<T> = iSubscriberListener
    var progressDialog : ProgressDialog? = null

    constructor(context: Context , iSubscriberListener : ISubscriberListener<T>, isOutSizeCancle:Boolean) : this(iSubscriberListener){

        //若IProgressDialog为抽象接口   则为object:IProgressDialog()
        //接口是，先有 object ，然后让 object 实现该接口；
        //抽象类是，先实现抽象类中的抽象方法，用构造方法构造出一个对象后，再给到 object

        this.progressDialog = ProgressDialog(
                context,
                isOutSizeCancle,
                object : IProgressDialog{
                    override fun cancleProgress() {
                        unsubscribe()
                    }
                }
        )

        this.context = context

    }

    override fun onStart() {
        super.onStart()
        if(progressDialog != null ){
            progressDialog!!.showProgress()
        }
    }

    override fun onNext(t: T) {
        var result  = t as BaseResultEntity
        if (result.errorCode==400){
            onException(APIException.DATA_ERROR,result.errorMsg)
        }else{
            iSubscriberListener.onSuccess(t)
        }
    }

    override fun onCompleted() {
        if(progressDialog != null ){
            progressDialog!!.closeProgress()
        }
    }

    override fun onError(e: Throwable?) {
        if(progressDialog != null ){
            progressDialog!!.closeProgress()
        }

        if (e is SocketException) {
            onException(APIException.CONNECT_ERROR)
        } else if (e is HttpException) {
            // HTTP错误
            onException(APIException.BAD_NETWORK)
        } else if (e is UnknownHostException) {
            // 无法解析该域名
            onException(APIException.UNKNOWNHOST_ERROR)
        } else if (e is InterruptedIOException) {
            // 连接超时
            onException(APIException.CONNECT_TIMEOUT)
        } else if (e is JsonParseException || e is JSONException ||
                e is ParseException || e is JsonSerializer<*> ||
                e is NotSerializableException) {
            // 解析错误
            onException(APIException.PARSE_ERROR)
        } else {
            onException(APIException.UNKNOWN_ERROR)
        }

    }

    /**
     * 通过Error信息获取Error描述语
     */
    private fun onException(@APIException.Error reason: String, vararg note: String) {
        if (reason == APIException.DATA_ERROR) {
            iSubscriberListener.onError(note[0])
        } else {
            iSubscriberListener.onError(APIException.getStatusDesc(reason))
        }
    }



}