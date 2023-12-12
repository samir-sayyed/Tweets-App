package com.sam.tweetsy.data.api

import com.sam.tweetsy.data.model.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyAPI {

    @GET("/v3/b/6575df3f54105e766fdc62d7?meta=false")
    suspend fun getTweets(
        @Header("X-JSON-Path") category: String
    ): Response<List<TweetListItem>>

    @GET("/v3/b/6575df3f54105e766fdc62d7?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories(): Response<List<String>>

}