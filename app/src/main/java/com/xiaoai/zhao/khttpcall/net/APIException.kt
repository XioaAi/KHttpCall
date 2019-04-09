package com.xiaoai.zhao.khttpcall.net

import android.support.annotation.StringDef

class APIException{

    //const val 可见性为public final static，可以直接访问。
    //val 可见性为private final static，并且val 会生成方法getNormalObject() ，通过方法调用访问

    companion object {
        /**
         * 解析数据失败
         */
        const val PARSE_ERROR = "parse_error"
        /**
         * 网络问题
         */
        const val BAD_NETWORK = "bad_network"

        /**
         * 未知主机错误
         */
        const val UNKNOWNHOST_ERROR = "unknownhost_error"

        /**
         * 连接错误
         */
        const val CONNECT_ERROR = "connect_error"

        /**
         * 连接超时
         */
        const val CONNECT_TIMEOUT = "connect_timeout"
        /**
         * baseEntity中的状态码非成功
         */
        const val DATA_ERROR = "data_error"
        /**
         * 未知错误
         */
        const val UNKNOWN_ERROR = "unknown_error"

        fun getStatusDesc(@Error errorInfo : String) : String{
            var desc = ""
            when (errorInfo) {
                PARSE_ERROR -> desc = "解析服务器响应数据失败"
                BAD_NETWORK -> desc = "服务器异常"
                UNKNOWNHOST_ERROR -> desc = "无法解析该主机域名"
                CONNECT_ERROR -> desc = "网络连接失败,请检查网络"
                UNKNOWN_ERROR -> desc = "未知错误"
                CONNECT_TIMEOUT -> desc = "连接超时,请稍后再试"
                DATA_ERROR -> desc = ""
            }
            return desc
        }
    }

    @StringDef(PARSE_ERROR, BAD_NETWORK, UNKNOWNHOST_ERROR, CONNECT_ERROR, CONNECT_TIMEOUT, DATA_ERROR, UNKNOWN_ERROR)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Error

}
