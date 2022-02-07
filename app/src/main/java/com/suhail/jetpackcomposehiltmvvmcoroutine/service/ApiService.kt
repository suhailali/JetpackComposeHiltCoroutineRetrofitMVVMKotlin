package com.suhail.jetpackcomposehiltmvvmcoroutine.service

import com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home.Ticker
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/exchange/ticker")
    suspend fun getTicker() : Response<List<Ticker>>

    @GET("/exchange/v1/markets")
    suspend fun getMarkets() : Result<List<String>>
}