package com.example.andromangler.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.andromangler.ui.ImageCard
import com.example.andromangler.ui.viewModel.ChapterViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterScreen(navController: NavController, chapterViewModel: ChapterViewModel, chapterToken: String){
    val isLoading = remember { mutableStateOf(false) }

    chapterViewModel.getChapter(chapterToken)
    val chapter by chapterViewModel.chapter.collectAsState()
    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
            title = { Text( ("Chapitre " + chapter.number + chapter.title), fontWeight = FontWeight.Bold) }
        )
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isLoading.value),
            onRefresh = {
                isLoading.value = true
                chapterViewModel.getChapter(chapterToken)
                isLoading.value = false
                        },
            indicator = {state, refreshTrigger -> SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
            }
        ){
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFFFFF))
            ){
                    item{
                        Row(modifier = Modifier.padding(bottom = 10.dp).weight(1f)) {
                            Text(text ="Site Web: ", fontWeight = FontWeight.Bold, color = Color.Blue)
                            Text(chapter.website_name, color = Color.Blue,
                                //                        modifier = Modifier.clickable { navController.navigate(
                                //                            Screens.WebsiteArticleList.route + "/${article.website_token}")
                                //                        }
                            )
                        }
                        Row(modifier = Modifier.padding(bottom = 10.dp).weight(1f)) {
                            Text(text ="Manga: ", fontWeight = FontWeight.Bold, color = Color.Blue)
                            Text(chapter.manga_name, color = Color.Blue,
                                //                        modifier = Modifier.clickable { navController.navigate(
                                //                            Screens.WebsiteArticleList.route + "/${article.website_token}")
                                //                        }
                            )
                        }
                    }
                    items(chapter.images) {chapterImage: String ->
                        ImageCard(imageUrl = chapterImage)
                    }
                }
        }

    }
}