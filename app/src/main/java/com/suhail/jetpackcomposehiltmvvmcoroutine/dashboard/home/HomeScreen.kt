package com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.suhail.jetpackcomposehiltmvvmcoroutine.R
import com.suhail.jetpackcomposehiltmvvmcoroutine.commonui.CircleProgressBar

@ExperimentalFoundationApi
@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeScreenViewModel) {
    val tickers = viewModel.uiState
    val filteredList = viewModel.filterItems
    val showProgress = viewModel.progressBar
//    println("Suhail Ali ${tickers.size}")
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = stringResource(id = R.string.homescreen), textAlign = TextAlign.Center, modifier = Modifier.weight(.1f))
            Button(onClick = { viewModel.getTicker() }, modifier = Modifier.weight(.1f)) {
                Text(text = "Show Tickers")
            }
        }

        if (tickers.value is HomeScreenViewModel.UiState.Loading) {
            CircleProgressBar()
        }

        Row {
            if (tickers.value is HomeScreenViewModel.UiState.Success) {
                val list = (tickers.value as HomeScreenViewModel.UiState.Success).value
                LazyVerticalGrid(cells = GridCells.Fixed(2), modifier = Modifier.testTag("TickerList"))
                {
                    items(list) { ticker ->
                        TickerItem(ticker = ticker)
                    }
                }
            }
            if(filteredList.value is HomeScreenViewModel.UiState.Success) {
                val list = (filteredList.value as HomeScreenViewModel.UiState.Success).value
                LazyColumn(modifier = Modifier.testTag("FilteredList")) {
                    items(list) { ticker ->
                        TickerItem(ticker = ticker)
                    }
                }
            }
        }
    }
}

@Composable
fun TickerItem(ticker: Ticker) {
    Box {
        Text(text = ticker.market)
        TickerImage("https://picsum.photos/200/300")
        Spacer(modifier = Modifier.size(20.dp))
    }
}

@Composable
fun TickerImage(url: String) {
    Image(painter = rememberImagePainter(data = url)
        , contentDescription = url, modifier = Modifier.size(128.dp))
}