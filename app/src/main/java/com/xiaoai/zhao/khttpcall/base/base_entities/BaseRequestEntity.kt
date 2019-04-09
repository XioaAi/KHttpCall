package com.xiaoai.zhao.khttpcall.base.base_entities

open class BaseRequestEntity(){

    lateinit var platform : String
    lateinit var deviceToken : String
    lateinit var appVersion : String



    constructor(platform: String, deviceToken: String, appVersion: String) :this() {
        this.platform = platform
        this.deviceToken = deviceToken
        this.appVersion = appVersion
    }


}
