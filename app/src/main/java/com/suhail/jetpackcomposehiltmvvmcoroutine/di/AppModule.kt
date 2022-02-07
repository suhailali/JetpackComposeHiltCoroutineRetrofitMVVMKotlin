package com.suhail.jetpackcomposehiltmvvmcoroutine.di

import com.suhail.jetpackcomposehiltmvvmcoroutine.BuildConfig
import com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home.HomeScreenRepository
import com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home.HomeScreenRepositoryImpl
import com.suhail.jetpackcomposehiltmvvmcoroutine.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideHomeScreenRepository(apiService: ApiService): HomeScreenRepository {
        return HomeScreenRepositoryImpl(apiService = apiService)
    }
    
}