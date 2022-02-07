package com.suhail.jetpackcomposehiltmvvmcoroutine.di

import com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home.HomeScreenRepository
import com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home.HomeScreenRepositoryImpl
import com.suhail.jetpackcomposehiltmvvmcoroutine.service.ApiService
import com.suhail.jetpackcomposehiltmvvmcoroutine.util.FakeHomeScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    fun provideHomeScreenRepository(): HomeScreenRepository {
        return FakeHomeScreenRepository()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}