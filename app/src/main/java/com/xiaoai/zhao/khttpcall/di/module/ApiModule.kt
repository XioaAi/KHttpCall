package com.xiaoai.zhao.khttpcall.di.module

import android.provider.SyncStateContract
import android.util.Log
import com.xiaoai.zhao.khttpcall.api.XiaoAiApi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = arrayOf(AppModule::class))
class ApiModule {

    @Provides
    @Singleton
    fun providerRetrofit(client:OkHttpClient,baseUrl:String)=
        Retrofit.Builder().client(client).baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

    @Provides
    @Singleton
    fun providerOkHttpClient(interceptor: HttpLoggingInterceptor)=
        OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS)
                .writeTimeout(100,TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun providerInterceptor() : HttpLoggingInterceptor{
        var interceptor = HttpLoggingInterceptor{
            Log.i("okhttp",it)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providerBaseUrl() = HttpUrl.parse("https://www.wanandroid.com/").toString()


    @Provides
    @Singleton
    fun providerXiaoAiApi(retrofit: Retrofit) = retrofit.create(XiaoAiApi::class.java)




}
