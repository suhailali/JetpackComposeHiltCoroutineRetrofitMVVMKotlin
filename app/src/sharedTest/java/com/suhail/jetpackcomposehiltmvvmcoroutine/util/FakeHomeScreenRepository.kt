package com.suhail.jetpackcomposehiltmvvmcoroutine.util

import com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home.HomeScreenRepository
import com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home.Ticker
import retrofit2.Response

class FakeHomeScreenRepository : HomeScreenRepository {

    private val list = mutableListOf<Ticker>()

    override suspend fun getTicker(): Response<List<Ticker>> {
        val ticker = Ticker(
            market = "BTCINR",
            change_24_hour = "1.455",
            high = "3095000.0",
            low = "2991233.9",
            volume = "102390399.08529398",
            last_price = "3061484.38",
            bid = "3064254.720000",
            ask = "3075039.980000",
            timestamp = 1643523150L,
        )
        list.add(ticker)
        return Response.success(list)
    }

    override suspend fun getMarkets(): Result<List<String>> {
        return Result.success(listOf("\"GLMBTC\",\"MCUSDT\",\"COTIBTC\",\"KAVABTC\",\"PIVXBTC\",\"GRTETH\",\"CLVBNB\",\"IOTAETH\",\"CNDBTC\",\"XNOUSDT\",\"RAREBNB\",\"XRPBNB\",\"ZENBNB\""))
    }

    override suspend fun getFilteredList(): List<Ticker> {
        val ticker = Ticker(
                market = "BTCINR",
        change_24_hour = "1.455",
        high = "3095000.0",
        low = "2991233.9",
        volume = "102390399.08529398",
        last_price = "3061484.38",
        bid = "3064254.720000",
        ask = "3075039.980000",
        timestamp = 1643523150L,
        )
        list.add(ticker)
        return list
    }

    fun addListItems(list_: List<Ticker>) {
        list.addAll(list_)
    }
}