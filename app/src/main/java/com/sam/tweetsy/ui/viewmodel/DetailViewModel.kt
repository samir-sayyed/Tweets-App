package com.sam.tweetsy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.tweetsy.data.model.TweetListItem
import com.sam.tweetsy.data.repository.TweetRepository
import com.sam.tweetsy.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _tweets = MutableLiveData<List<TweetListItem>?>(emptyList())
    val tweets: LiveData<List<TweetListItem>?> = _tweets

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        val category = savedStateHandle.get<String>("category")
        if (category != null) {
            getTweets(category)
        }
    }


    private fun getTweets(category: String) = viewModelScope.launch {
        repository.getTweets(category).collectLatest {
            when (it) {
                is ApiResult.Success -> if (it.data != null) _tweets.postValue(
                    it.data
                )
                is ApiResult.Error -> _error.postValue(
                    it.message ?: "Something went wrong"
                )
                else -> Log.i("getTweets", "getTweets loading")
            }
        }
    }

}