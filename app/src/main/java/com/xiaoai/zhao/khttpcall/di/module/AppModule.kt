package com.xiaoai.zhao.khttpcall.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context){
    @Provides
    fun providerContext() = context
}