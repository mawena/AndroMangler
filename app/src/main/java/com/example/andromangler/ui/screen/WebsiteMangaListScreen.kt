package com.example.andromangler.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.andromangler.domain.item.MangaItem
import com.example.andromangler.ui.ImageCard
import com.example.andromangler.ui.viewModel.MangaViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebsiteMangaListScreen(navController: NavController, mangaViewModel: MangaViewModel, websiteToken: String){
    val isLoading = remember { mutableStateOf(false) }

    mangaViewModel.getMangaListByWebsite(websiteToken)
    val mangaList by mangaViewModel.mangaList.collectAsState()
    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
            title = { Text("Mangas", fontWeight = FontWeight.Bold) }
        )
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isLoading.value),
            onRefresh = {
                isLoading.value = true
                mangaViewModel.getMangaListByWebsite(websiteToken)
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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(mangaList) { manga: MangaItem ->
                    MangaCard(navController, manga)
                }
            }
        }
    }
}

@Composable
fun MangaCard(navController: NavController, manga: MangaItem) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp)
            .fillMaxSize()
            .clickable { navController.navigate(Screens.MangaChapterList.route + "/${manga.token}") }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
        ) {
            Text(
                text = manga.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp)
            )
            ImageCard(manga.cover_page)
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFFFFF))
                    .padding(10.dp)
            ){
                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(text ="Genre: ", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(manga.gender, color = Color.Blue,
//                        modifier = Modifier.clickable { navController.navigate(
//                            Screens.MangaChapterList.route + "/${manga.token}")
//                        }
                    )
                }

            }
            Text(text ="Description: ", fontWeight = FontWeight.Bold, color = Color.Black, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))
            Text(manga.description, color = Color.Black, modifier = Modifier.padding(10.dp))
        }
    }
}
