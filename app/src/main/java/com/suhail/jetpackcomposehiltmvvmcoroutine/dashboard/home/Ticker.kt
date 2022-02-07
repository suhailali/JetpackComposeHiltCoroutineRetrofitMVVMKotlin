package com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home



//"market": "REQBTC",
//"change_24_hour": "-1.621",
//"high": "0.00002799",
//"low": "0.00002626",
//"volume": "14.10",
//"last_price": "0.00002663",
//"bid": "0.00002663",
//"ask": "0.00002669",
//"timestamp": 1524211224
data class Ticker(
    var market: String,
    var change_24_hour: String,
    var high: String,
    var low: String,
    var volume: String,
    var last_price: String,
    var bid: String,
    var ask: String,
    var timestamp: Long
)
