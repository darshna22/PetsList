package com.my.sapiaassignment

import android.app.Application
import com.my.sapiaassignment.base.di.DaggerMyAppComponent
import com.my.sapiaassignment.base.di.MyAppComponent
import com.my.sapiaassignment.base.di.MyAppModule

class MyApplication : Application() {

    lateinit var appComponent: MyAppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = initDagger(this)
    }

    private fun initDagger(app: MyApplication): MyAppComponent =
        DaggerMyAppComponent.builder()
            .myAppModule(MyAppModule(app))
            .build()
}