package com.sam.tweetsy.data.repository

import android.content.Context
import com.sam.tweetsy.base.BaseApiRepository
import com.sam.tweetsy.data.api.TweetsyAPI
import com.sam.tweetsy.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TweetRepository @Inject constructor(
    private val tweetsyAPI: TweetsyAPI,
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseApiRepository(context) {

    fun getCategories() = flow {
        val response = safeApiCall {
            tweetsyAPI.getCategories()
        }
       emit(response)
    }.flowOn(ioDispatcher)


    fun getTweets(category: String) = flow {
        val response = safeApiCall {
            tweetsyAPI.getTweets("tweets[?(@.category==\"${category}\")]")
        }
        emit(response)
    }.flowOn(ioDispatcher)

}