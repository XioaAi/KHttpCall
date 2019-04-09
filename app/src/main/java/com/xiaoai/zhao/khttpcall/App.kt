package com.xiaoai.zhao.khttpcall

import android.app.Application
import com.xiaoai.zhao.khttpcall.di.component.ApiComponent
import com.xiaoai.zhao.khttpcall.di.component.DaggerApiComponent
import com.xiaoai.zhao.khttpcall.di.module.ApiModule
import com.xiaoai.zhao.khttpcall.di.module.AppModule
import javax.inject.Inject

class App : Application() {

    init {
        instance = this
    }

    @Inject
    lateinit var apiComponent:ApiComponent

    override fun onCreate() {
        super.onCreate()

        DaggerApiComponent.builder().apiModule(ApiModule()).appModule(AppModule(this)).build().inject(this)

    }

    companion object {
        lateinit var instance : App
    }

}