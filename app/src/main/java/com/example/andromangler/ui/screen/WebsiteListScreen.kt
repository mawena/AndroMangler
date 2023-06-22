package com.example.andromangler.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.andromangler.app.navigation.Screens
import com.example.andromangler.domain.item.WebsiteItem
import com.example.andromangler.ui.viewModel.WebsiteViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebsiteListScreen(navController: NavController, websiteViewModel: WebsiteViewModel){
    val isLoading = remember { mutableStateOf(false) }

    val websiteList by websiteViewModel.websiteList.collectAsState()
    Scaffold(
        topBar = { TopAppBar({ Text("Sites Web", fontWeight = FontWeight.Bold) })},
        modifier = Modifier.background(Color.Blue)
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isLoading.value),
            onRefresh = {
                isLoading.value = true
                websiteViewModel.getWebsiteList()
                isLoading.value = false
                        },
            indicator = {state, refreshTrigger -> SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
            }
        ) {
            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            val halfScreenWidth = (screenWidth / 2).coerceAtMost(screenWidth)
            LazyColumn(
                modifier = Modifier
                    .padding(top = 70.dp)
                    .background(Color.Gray)
                    .fillMaxSize()
            ) {
                itemsIndexed(websiteList) { index, website: WebsiteItem ->
                    if (index % 2 == 0) {
                        Row(modifier = Modifier.fillParentMaxWidth()) {
                            Box(modifier = Modifier
                                .width(halfScreenWidth)
                            ){
                                WebsiteCard(navController, website)
                            }
                            if (index + 1 < websiteList.size) {
                                Box(modifier = Modifier
                                    .width(halfScreenWidth)
                                ){
                                    WebsiteCard(navController, websiteList[index + 1])
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
fun WebsiteCard(navController: NavController, website: WebsiteItem) {
    Card(
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .padding(top = 7.dp, bottom = 7.dp, start = 14.dp, end = 14.dp)
            .fillMaxSize()
            .clickable {
                navController.navigate(Screens.WebsiteMangaList.route + "/${website.token}")
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
        ) {
            Text(
                text = website.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                Text(text ="Lien: ", fontWeight = FontWeight.Bold, color = Color.Black)
                Text(website.link, color = Color.Black)
            }
        }
    }
}
