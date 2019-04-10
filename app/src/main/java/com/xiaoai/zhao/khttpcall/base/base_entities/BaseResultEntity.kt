package com.xiaoai.zhao.khttpcall.base.base_entities

open class BaseResultEntity<T>{
    var errorCode: Int = 0
    var errorMsg: String = ""

    var data: T? = null
}