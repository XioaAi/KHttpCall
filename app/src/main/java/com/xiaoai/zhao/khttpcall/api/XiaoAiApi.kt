package com.xiaoai.zhao.khttpcall.api

import com.xiaoai.zhao.khttpcall.base.base_entities.BaseResultEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface XiaoAiApi {

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") username:String,
              @Field("password") password:String): Observable<BaseResultEntity>

}