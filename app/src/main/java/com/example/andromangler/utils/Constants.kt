package com.example.andromangler.utils

class Constants {
    companion object {
        private const val BASE_ADDRESS = "192.168.1.35"
        private const val BASE_PORT = "8000"
        const val BASE_API_URL = "http://$BASE_ADDRESS:$BASE_PORT/api/"

        const val WEBSITE_LIST_ENDPOINT="website"
        const val MANGA_LIST_ENDPOINT="manga"
        const val MANGA_CHAPTER_LIST_ENDPOINT="chapter"
        const val CHAPTER_ENDPOINT="chapter/{chapter_token}"
    }

    object Screencs{
        const val HOME_SCREEN="home_screen"
        const val WEBSITE_LIST_SCREEN="website_list_screen"
        const val MANGA_LIST_SCREEN="manga_list_screen"
        const val WEBSITE_MANGA_LIST_SCREEN="website_manga_list_screen"
        const val MANGA_CHAPTER_LIST_SCREEN="manga_chapter_list_screen"
        const val CHAPTER_SCREEN="chapter_screen"
    }
}