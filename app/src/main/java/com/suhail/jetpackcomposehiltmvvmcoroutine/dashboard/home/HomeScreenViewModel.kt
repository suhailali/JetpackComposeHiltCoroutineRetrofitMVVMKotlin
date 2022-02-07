package com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: HomeScreenRepository) :
    ViewModel() {

    var uiState = mutableStateOf<UiState<List<Ticker>>>(UiState.Empty)
        private set

    var filterItems = mutableStateOf<UiState<List<Ticker>>>(UiState.Empty)
        private set

    var progressBar = mutableStateOf<UiState<Boolean>>(UiState.Empty)
        private set

    init {
        println("getTicker Viewmodel ${this.toString()}")
        getTicker()
        getFilteredList()
    }

    fun getTicker() {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            repository.getTicker().let {
                if (it.isSuccessful) {
                    it.body()?.let { tickerList ->
                        uiState.value = UiState.Success(tickerList)
                    }
                } else {
                    uiState.value = UiState.Error(it.message())
                    print(it.message())
                }
            }
        }
    }

    fun getFilteredList() {
        CoroutineScope(Dispatchers.IO).launch {
//            println("getTickers1 Thread name: ${Thread.currentThread().name}")
            viewModelScope.launch {
//                println("getTickers2 Thread name: ${Thread.currentThread().name}")
                filterItems.value = UiState.Loading
                repository.getFilteredList().let {
                    filterItems.value = UiState.Success(it)
                }
            }.invokeOnCompletion {
//                println("getTicker OnCompletion job")
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        println("getTicker Oncleared ${this.toString()}")
    }

    sealed class UiState<out T : Any> {
        data class Success<out T: Any>(val value: T) : UiState<T>()
        data class Error(val msg: String) : UiState<Nothing>()
        object Loading : UiState<Nothing>()
        object Empty : UiState<Nothing>()
    }
}