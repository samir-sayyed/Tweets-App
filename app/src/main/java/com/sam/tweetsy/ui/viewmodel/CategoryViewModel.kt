package com.sam.tweetsy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.tweetsy.data.repository.TweetRepository
import com.sam.tweetsy.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: TweetRepository
) : ViewModel() {

    private val _categories = MutableLiveData<List<String>?>()
    val categories: LiveData<List<String>?> = _categories

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        repository.getCategories().collectLatest { response ->
            when (response) {
                is ApiResult.Success -> if (response.data != null) _categories.postValue(
                    response.data
                )

                is ApiResult.Error -> _error.postValue(
                    response.message ?: "Something went wrong"
                )
                else -> Log.i("getCategories", "getCategories loading")
            }
        }
    }

}