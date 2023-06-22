package com.example.andromangler.app.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.andromangler.ui.screen.ChapterScreen
import com.example.andromangler.ui.screen.MangaChapterListScreen
import com.example.andromangler.ui.screen.WebsiteMangaListScreen
import com.example.andromangler.ui.screen.WebsiteListScreen
import com.example.andromangler.ui.viewModel.ChapterViewModel
import com.example.andromangler.ui.viewModel.MangaViewModel
import com.example.andromangler.ui.viewModel.WebsiteViewModel
import com.example.andromangler.utils.Constants.Screencs.CHAPTER_SCREEN
import com.example.andromangler.utils.Constants.Screencs.HOME_SCREEN
import com.example.andromangler.utils.Constants.Screencs.MANGA_CHAPTER_LIST_SCREEN
import com.example.andromangler.utils.Constants.Screencs.MANGA_LIST_SCREEN
import com.example.andromangler.utils.Constants.Screencs.WEBSITE_LIST_SCREEN
import com.example.andromangler.utils.Constants.Screencs.WEBSITE_MANGA_LIST_SCREEN

sealed class Screens (val route: String){
    object Home: Screens(route=HOME_SCREEN)
    object WebsiteList: Screens(route=WEBSITE_LIST_SCREEN)
    object MangaList: Screens(route=MANGA_LIST_SCREEN)
    object WebsiteMangaList: Screens(route=WEBSITE_MANGA_LIST_SCREEN)
    object MangaChapterList: Screens(route=MANGA_CHAPTER_LIST_SCREEN)
    object Chapter: Screens(route=CHAPTER_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, websiteViewModel: WebsiteViewModel, mangaViewModel: MangaViewModel, chapterViewModel: ChapterViewModel){
    NavHost(navController = navController, startDestination = Screens.WebsiteList.route){
        composable(Screens.WebsiteList.route){
            WebsiteListScreen(navController = navController, websiteViewModel = websiteViewModel)
        }

        composable(
            "${Screens.WebsiteMangaList.route}/{websiteToken}",
            arguments = listOf(navArgument("websiteToken") { NavType.StringType })
        ) { backStackEntry ->
            val websiteToken = backStackEntry.arguments?.getString("websiteToken")

            WebsiteMangaListScreen(navController = navController, mangaViewModel = mangaViewModel, websiteToken = websiteToken!!)
        }

        composable(
            "${Screens.MangaChapterList.route}/{mangaToken}",
            arguments = listOf(navArgument("mangaToken") { NavType.StringType })
        ) { backStackEntry ->
            val mangaToken = backStackEntry.arguments?.getString("mangaToken")

            MangaChapterListScreen(navController = navController, chapterViewModel = chapterViewModel, mangaToken = mangaToken!!)
        }

        composable(
            "${Screens.Chapter.route}/{chapterToken}",
            arguments = listOf(navArgument("chapterToken") { NavType.StringType })
        ) { backStackEntry ->
            val chapterToken = backStackEntry.arguments?.getString("chapterToken")
            ChapterScreen(navController = navController, chapterViewModel = chapterViewModel, chapterToken = chapterToken!!)
        }

    }
}