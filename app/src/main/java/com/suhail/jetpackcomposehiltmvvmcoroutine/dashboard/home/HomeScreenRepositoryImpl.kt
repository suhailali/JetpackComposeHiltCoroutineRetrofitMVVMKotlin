package com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home

import com.suhail.jetpackcomposehiltmvvmcoroutine.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class HomeScreenRepositoryImpl @Inject constructor(private val apiService: ApiService): HomeScreenRepository {
    override suspend fun getTicker(): Response<List<Ticker>> {
//        println("getTickers Thread name: ${Thread.currentThread().name}")
        return apiService.getTicker()
    }

    override suspend fun getMarkets(): Result<List<String>> {
        return apiService.getMarkets()
    }

    override suspend fun getFilteredList(): List<Ticker> {
        return withContext(Dispatchers.IO) {
            delay(10000)
            getTicker().body()?.take(5)?: listOf()
        }
//        return withContext(Dispatchers.IO) {
//            getTicker().body()?.take(5) ?: listOf()
//        }
    }
}