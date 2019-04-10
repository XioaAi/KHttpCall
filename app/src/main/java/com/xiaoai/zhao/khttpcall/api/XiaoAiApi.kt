package com.xiaoai.zhao.khttpcall.api

import com.xiaoai.zhao.khttpcall.base.base_entities.BaseResultEntity
import com.xiaoai.zhao.khttpcall.entities.LoginReqEntity
import com.xiaoai.zhao.khttpcall.entities.LoginResultEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface XiaoAiApi {

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") username:String,
              @Field("password") password:String): Observable<BaseResultEntity<LoginResultEntity>>

    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("username") username:String,
              @Field("password") password:String,
                 @Field("repassword") repassword:String): Observable<BaseResultEntity<LoginResultEntity>>

}