package com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home

import retrofit2.Response

interface HomeScreenRepository {
    suspend fun getTicker(): Response<List<Ticker>>

    suspend fun getMarkets(): Result<List<String>>

    suspend fun getFilteredList(): List<Ticker>
}