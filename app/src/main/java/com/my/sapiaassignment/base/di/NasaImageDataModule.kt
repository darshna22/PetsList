package com.my.sapiaassignment.base.di

import com.my.sapiaassignment.datalayer.repository.petslist.PetsRepository
import com.my.sapiaassignment.datalayer.repository.petslist.PetsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NasaImageDataModule {
    @Binds
    abstract fun provideNasaImageDataViewModel(nasaImageRepositoryImpl: PetsRepositoryImpl): PetsRepository

}