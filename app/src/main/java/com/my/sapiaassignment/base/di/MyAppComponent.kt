package com.my.sapiaassignment.base.di

import com.my.sapiaassignment.datalayer.repository.petslist.PetsRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MyAppModule::class, NasaImageDataModule::class, RetrofitModule::class])
interface MyAppComponent {
    val petsRepository: PetsRepository
}

