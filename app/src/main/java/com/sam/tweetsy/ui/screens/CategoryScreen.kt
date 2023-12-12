package com.sam.tweetsy.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sam.tweetsy.R
import com.sam.tweetsy.ui.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories = categoryViewModel.categories.observeAsState()

    if (categories.value == null){
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
        }
    }else{
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            items(categories.value?.distinct() ?: arrayListOf()) { category ->
                CategoryItem(category = category, onClick)
            }
        }
    }

}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .clickable {
                onClick(category)
            }
            .size(140.dp)
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(id = R.drawable.layered_waves_haikei),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color(0xFFEEEEEE)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 22.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}