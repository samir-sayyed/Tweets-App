package com.sam.tweetsy.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sam.tweetsy.R
import com.sam.tweetsy.ui.viewmodel.DetailViewModel

@Composable
fun DetailScreen() {
    val detailViewModel: DetailViewModel = hiltViewModel()
    val tweets = detailViewModel.tweets.observeAsState()
    if (tweets.value == null){
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
        }
    }else{
        LazyColumn(content = {
            items(tweets.value ?: arrayListOf()) { tweet ->
                TweetListItem(tweet = tweet.tweet.toString())
            }
        })
    }
}

@Composable
fun TweetListItem(tweet: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ,
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        content = {
            Text(
                text = tweet,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}