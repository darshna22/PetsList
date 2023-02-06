package com.my.sapiaassignment.base.di.activity

import android.app.Activity
import com.my.sapiaassignment.base.di.MyAppComponent
import com.my.sapiaassignment.uilayer.petslist.PetsListFragment
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [MyAppComponent::class])
interface ActivityComponent {
    fun inject(petsListFragment: PetsListFragment)
    val activity: Activity
}

