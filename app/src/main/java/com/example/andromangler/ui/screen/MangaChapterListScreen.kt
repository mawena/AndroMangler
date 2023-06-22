package com.example.andromangler.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.andromangler.app.navigation.Screens
import com.example.andromangler.domain.item.ChapterItem
import com.example.andromangler.domain.item.toChapterItem
import com.example.andromangler.ui.ImageCard
import com.example.andromangler.ui.viewModel.ChapterViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaChapterListScreen(navController: NavController, chapterViewModel: ChapterViewModel, mangaToken: String){
    val isLoading = remember { mutableStateOf(false) }

    chapterViewModel.getChapterListByManga(mangaToken)
    val chapterList by chapterViewModel.chapterList.collectAsState()
    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
            title = { Text( if (chapterList.isEmpty()) "Erreur de chargement" else chapterList[0].manga_name, fontWeight = FontWeight.Bold) }
        )
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isLoading.value),
            onRefresh = {
                isLoading.value = true
                chapterViewModel.getChapterListByManga(mangaToken)
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
            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            val halfScreenWidth = (screenWidth / 2).coerceAtMost(screenWidth)
            LazyColumn{
                itemsIndexed(chapterList) {index, chapterItem: ChapterItem ->
                    if (index % 2 == 0) {
                        Row(modifier = Modifier.fillParentMaxWidth()) {
                            Box(
                                modifier = Modifier
                                    .width(halfScreenWidth)
                            ) {
                                ChapterCard(navController, chapterItem)
                            }
                            if (index + 1 < chapterList.size) {
                                Box(
                                    modifier = Modifier
                                        .width(halfScreenWidth)
                                ) {
                                    ChapterCard(navController, chapterList[index + 1])
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChapterCard(navController: NavController, chapterItem: ChapterItem) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp)
            .fillMaxSize()
        .clickable { navController.navigate(Screens.Chapter.route + "/${chapterItem.token}") }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
        ) {
            Text(
                text = ("Chapitre " + chapterItem.number + chapterItem.title) ,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp)
            )
            ImageCard(chapterItem.cover_page)
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFFFFF))
                    .padding(10.dp)
            ){
                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(text ="Site Web: ", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(chapterItem.website_name, color = Color.Blue,
//                        modifier = Modifier.clickable { navController.navigate(
//                            Screens.WebsiteArticleList.route + "/${article.website_token}")
//                        }
                    )
                }

                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(text ="Date: ", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(chapterItem.scraped_at, color = Color.Black)
                }
            }
        }
    }
}
