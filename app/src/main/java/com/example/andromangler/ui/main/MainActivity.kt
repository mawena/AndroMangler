package com.example.andromangler.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.andromangler.app.navigation.SetupNavHost
import com.example.andromangler.ui.screen.MangaChapterListScreen
import com.example.andromangler.ui.screen.WebsiteListScreen
import com.example.andromangler.ui.theme.AndroManglerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroManglerTheme {
//                 A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SetupNavHost(
                        navController = navController,
                        websiteViewModel = viewModel(),
                        mangaViewModel = viewModel(),
                        chapterViewModel = viewModel()
                    )
                }
            }
        }
    }
}