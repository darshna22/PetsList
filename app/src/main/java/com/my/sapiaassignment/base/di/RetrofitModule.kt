package com.my.sapiaassignment.base.di

import com.my.sapiaassignment.base.network.RetrofitService
import com.my.sapiaassignment.datalayer.ApiService
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofitService(retrofitBuilder: RetrofitService): ApiService =
        retrofitBuilder.buildAPI(ApiService::class.java)
}